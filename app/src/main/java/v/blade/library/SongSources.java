package v.blade.library;

public class SongSources
{
    public static class SongSource
    {
        private Object id;
        private Source source;

        public SongSource(Object id, Source s)
        {
            this.id = id;
            this.source = s;
        }

        public Object getId() {return this.id;}
        public Source getSource() {return this.source;}
    }

    public SongSource sources[] = new SongSource[Source.SOURCES.length];

    public void addSource(SongSource toAdd)
    {
        if(sources[0] != null && sources[0].getSource() == toAdd.getSource()
                || sources[1] != null && sources[1].getSource() == toAdd.getSource()
                || sources[2] != null && sources[2].getSource() == toAdd.getSource()) return;

        if(sources[0] == null) {sources[0] = toAdd;}
        else if(sources[0].getSource().getPriority() < toAdd.getSource().getPriority())
        {
            sources[2] = sources[1];
            sources[1] = sources[0];
            sources[0] = toAdd;
        }
        else if(sources[1] == null) sources[1] = toAdd;
        else if(sources[1].getSource().getPriority() < toAdd.getSource().getPriority())
        {
            sources[2] = sources[1];
            sources[1] = toAdd;
        }
        else if(sources[2] == null) sources[2] = toAdd;
        else System.err.println("Warning : incorrect call to addSource().");
    }

    public SongSource getSourceByPriority(int priority)
    {
        return sources[priority];
    }
    public SongSource getSpotify()
    {
        for(SongSource s : sources)
            if(s != null && s.getSource() == Source.SOURCE_SPOTIFY) return s;
        return null;
    }
    public SongSource getDeezer()
    {
        for(SongSource s : sources)
            if(s != null && s.getSource() == Source.SOURCE_DEEZER) return s;
        return null;
    }
}
