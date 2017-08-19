package alex.carcar.kenken;

public class KenHood {
    public KenGroups groups;
    public KenSquare square;

    KenHood(KenGroups kg, KenSquare ks) {
        this.groups = kg;
        this.square = ks;
        int x = ks.x;
        int y = ks.y;
    }

    public boolean set(int group) {
        int x = square.x;
        int y = square.y;
        int ox = x;
        int oy = y;
        if (!groups.isEmpty(square)) return false;
        groups.set(square, group);
        switch ((int) Math.floor(Math.random() * 4)) {
            case 0:
                x--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                y--;
                break;
        }
        if (groups.set(x, y, group)) {
            switch ((int) Math.floor(Math.random() * 4)) {
                case 0:
                    x--;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    y++;
                    break;
                case 3:
                    y--;
                    break;
            }
            groups.set(x, y, group);
        } else {
            x = ox;
            y = oy;
            if (groups.set(x - 1, y, group)) return true;
            if (groups.set(x + 1, y, group)) return true;
            if (groups.set(x, y - 1, group)) return true;
            if (groups.set(x, y + 1, group)) return true;
        }
        return true;
    }
}