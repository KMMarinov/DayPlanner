package com.kalinmarinov.dayplanner.database.roomdatabases.typeconverters;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Kalin.Marinov on 26.12.2017.
 */
public class DateConvertersTest {

    @Test
    public void shouldConvertTimestampToDate() {
        // given
        final Long timestamp = 1514318848L;

        // when
        final Date date = DateConverters.fromTimestamp(timestamp);

        // then
        assertThat(new Date(timestamp), equalTo(date));
    }

    @Test
    public void shouldConvertDateToTimestamp() {
        // given
        final Date date = new Date(1514318848L);

        // when
        final Long timestamp = DateConverters.dateToTimestamp(date);

        // then
        assertThat(new Date(timestamp), equalTo(date));
    }
}