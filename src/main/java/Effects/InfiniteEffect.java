package Effects;

import entity.Playable;

public abstract class InfiniteEffect extends Effect{
    public InfiniteEffect(Playable owner, int level) {
        super(owner,-1,level);
    }
    public InfiniteEffect(Playable owner) {
        super(owner,-1);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
