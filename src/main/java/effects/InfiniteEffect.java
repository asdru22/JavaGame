package effects;

import entity.Playable;

public abstract class InfiniteEffect extends Effect{
    public InfiniteEffect(Playable owner, int level, boolean positive) {
        super(owner,-1,level, positive);
    }
    public InfiniteEffect(Playable owner, boolean positive) {
        super(owner,-1, positive);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
