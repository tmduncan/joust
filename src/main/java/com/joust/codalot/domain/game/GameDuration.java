package com.joust.codalot.domain.game;

import org.joda.time.DateTime;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GameDuration implements Iterable<DateTime> {
    private final DateTime start;
    private final DateTime end;

    public GameDuration(DateTime start,
                        DateTime end) {
        this.start = start;
        this.end = end;
    }

    public Iterator<DateTime> iterator()
    {
        return new DateTimeRangeIterator(start, end);
    }


    private static class DateTimeRangeIterator implements Iterator<DateTime>
    {
        private DateTime current;
        private final DateTime end;

        private DateTimeRangeIterator(DateTime start,
                                      DateTime end)
        {
            this.current = start;
            this.end = end;
        }

        public boolean hasNext()
        {
            return current != null;
        }

        public DateTime next()
        {
            if (current == null)
            {
                throw new NoSuchElementException();
            }
            DateTime ret = current;
            current = current.plusHours(1);
            if (current.compareTo(end) >= 0)
            {
                current = null;
            }
            return ret;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

}
