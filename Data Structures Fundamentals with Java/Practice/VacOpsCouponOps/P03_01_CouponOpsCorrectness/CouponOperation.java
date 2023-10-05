package couponsopsjava;

import java.util.*;
import java.util.stream.Collectors;


public class CouponOperation implements ICouponOperation {
    private List<Coupon> coupons;
    private Map<Website, List<Coupon>> websites;

    public CouponOperation() {
        this.coupons = new ArrayList<>();
        this.websites = new LinkedHashMap<>();
    }

    public void registerSite(Website w) {
        if (websites.keySet().stream().anyMatch(website -> website.domain.equals(w.domain))) {
            throw new IllegalArgumentException();
        }

        this.websites.put(w, new ArrayList<>());
    }

    public void addCoupon(Website w, Coupon c) {
        if (!this.exist(w)) {
            throw new IllegalArgumentException();
        }

        this.coupons.add(c);
        this.websites.get(w).add(c);
    }

    public Website removeWebsite(String domain) {
        Website website = this.websites.keySet().stream().filter(w -> w.domain.equals(domain)).findFirst().orElse(null);
        if (website == null) {
            throw new IllegalArgumentException();
        }

        for (Coupon coupon : this.websites.get(website)) {
            this.coupons.remove(coupon);
        }

        this.websites.remove(website);
        return website;
    }

    public Coupon removeCoupon(String code) {
        Coupon coupon = this.coupons.stream().filter(c -> c.code.equals(code)).findFirst().orElse(null);
        if (coupon == null) {
            throw new IllegalArgumentException();
        }

        this.coupons.remove(coupon);
        for (List<Coupon> list : this.websites.values()) {
            list.remove(coupon);
        }

        return coupon;
    }

    public boolean exist(Website w) {
        return this.websites.containsKey(w);
    }

    public boolean exist(Coupon c) {
        return this.coupons.contains(c);
    }

    public Collection<Website> getSites() {
        return Collections.unmodifiableCollection(this.websites.keySet());
    }

    public Collection<Coupon> getCouponsForWebsite(Website w) {
        if (!this.exist(w)) {
            throw new IllegalArgumentException();
        }

        return Collections.unmodifiableCollection(this.websites.get(w));
    }

    public void useCoupon(Website w, Coupon c) {
        if (!this.exist(w) || !this.exist(c) || !this.websites.get(w).contains(c)) {
            throw new IllegalArgumentException();
        }

        this.coupons.remove(c);
        this.websites.get(w).remove(c);
    }

    public Collection<Coupon> getCouponsOrderedByValidityDescAndDiscountPercentageDesc() {
        return this.coupons.stream()
                .sorted(Comparator.comparing((Coupon c) -> c.validity).reversed()
                        .thenComparing((Coupon c) -> c.discountPercentage).reversed())
                .collect(Collectors.toList());
    }

    public Collection<Website> getWebsitesOrderedByUserCountAndCouponsCountDesc() {
        return this.websites.keySet().stream().sorted(Comparator.comparing((Website w) -> w.usersCount)
                .thenComparing((Website w) -> this.websites.get(w).size()).reversed())
                .collect(Collectors.toList());
    }
}
