package core;

import com.sun.source.tree.Tree;
import models.Message;

import java.util.*;
import java.util.stream.Collectors;

public class DiscordImpl implements Discord {
    private HashMap<String, Message> idToMessage;
    private HashMap<String, HashSet<String>> channelToIds;
    private TreeSet<Message> messagesByTimestamp;
    private TreeSet<Message> messagesByCountReactions;
    private TreeSet<Message> messagesOrdered;

    public DiscordImpl() {
        this.idToMessage = new HashMap<>();
        this.channelToIds = new HashMap<>();

        Comparator<Message> comp1 = Comparator.comparing(Message::getTimestamp);
        this.messagesByTimestamp = new TreeSet<>(comp1);

        Comparator<Message> comp2 = Comparator.comparing((Message m) -> m.getReactions().size()).reversed();
        this.messagesByCountReactions = new TreeSet<>(comp2);

        Comparator<Message> comp3 = Comparator.comparing((Message m) -> m.getReactions().size()).reversed()
                .thenComparing(Message::getTimestamp)
                .thenComparing(m -> m.getContent().length());
        this.messagesOrdered = new TreeSet<>(comp3);
    }

    @Override
    public void sendMessage(Message message) {
        String id = message.getId();
        String channel = message.getChannel();

        this.idToMessage.put(id, message);
        this.channelToIds.putIfAbsent(channel, new HashSet<>());
        Set<String> channelIds = this.channelToIds.get(channel);
        channelIds.add(id);

        this.messagesByTimestamp.add(message);
        this.messagesByCountReactions.add(message);
        this.messagesOrdered.add(message);
    }

    @Override
    public boolean contains(Message message) {
        return this.idToMessage.containsKey(message.getId());
    }

    @Override
    public int size() {
        return this.idToMessage.size();
    }

    @Override
    public Message getMessage(String messageId) {
        Message message = this.idToMessage.get(messageId);
        if (message == null) {
            throw new IllegalArgumentException();
        }
        return message;
    }

    @Override
    public void deleteMessage(String id) {
        Message message = this.idToMessage.get(id);
        if (message == null) {
            throw new IllegalArgumentException();
        }
        String channel = message.getChannel();

        this.idToMessage.remove(id);
        Set<String> channelIds = this.channelToIds.get(channel);
        channelIds.remove(id);
        if (channelIds.isEmpty()) {
            this.channelToIds.remove(channel);
        }

        this.messagesByTimestamp.remove(message);
        this.messagesByCountReactions.remove(message);
        this.messagesOrdered.remove(message);
    }

    @Override
    public void reactToMessage(String id, String reaction) {
        Message message = this.idToMessage.get(id);
        if (message == null) {
            throw new IllegalArgumentException();
        }

        message.getReactions().add(reaction);
    }

    @Override
    public Iterable<Message> getChannelMessages(String channel) {
        Set<String> channelIds = this.channelToIds.get(channel);
        if (channelIds == null || channelIds.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return channelIds.stream().map(id -> this.idToMessage.get(id)).collect(Collectors.toList());
    }

    @Override
    public Iterable<Message> getMessagesByReactions(List<String> reactions) {
        Comparator<Message> comp = Comparator.comparing((Message m) -> m.getReactions().size()).reversed()
                .thenComparing(Message::getTimestamp);

        return this.idToMessage.values().stream()
                .filter(m -> new HashSet<>(m.getReactions()).containsAll(reactions))
                .sorted(comp).collect(Collectors.toList());
    }

    @Override
    public Iterable<Message> getMessageInTimeRange(Integer lowerBound, Integer upperBound) {
        return this.messagesByTimestamp.stream()
                .filter((Message m) -> m.getTimestamp() >= lowerBound && m.getTimestamp() <= upperBound)
                .sorted(Comparator.comparing((Message m) -> this.channelToIds.get(m.getId()).size()).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Message> getTop3MostReactedMessages() {
        return this.messagesByCountReactions.stream().limit(3).collect(Collectors.toList());
    }

    @Override
    public Iterable<Message> getAllMessagesOrderedByCountOfReactionsThenByTimestampThenByLengthOfContent() {
        return this.messagesOrdered;
    }
}
