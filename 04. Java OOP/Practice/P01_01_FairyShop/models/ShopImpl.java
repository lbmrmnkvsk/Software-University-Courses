package fairyShop.models;

import java.util.List;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {
        while (!present.isDone() && helper.canWork() &&
                helper.getInstruments().stream().filter(i -> !i.isBroken()).count() > 0) {

            Instrument currentInstrument = helper.getInstruments()
                    .stream().filter(i -> !i.isBroken()).findFirst().orElse(null);

            currentInstrument.use();
            helper.work();
            present.getCrafted();
        }
    }
}
