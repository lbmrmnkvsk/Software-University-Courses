package magicGame.models.magics;

public class BlackMagic extends MagicImpl {

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount() -10 < 0) {
            this.setBulletsCount(0);
            return 0;
        } else {
            this.setBulletsCount(this.getBulletsCount() - 10);
            return 10;
        }
    }
}
