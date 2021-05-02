package com.mironov.image.studio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum State {
    INACTIVE((short) 0),ACTIVE((short) 1);

    private static final Map<Short, State> LOOKUP;

    static {
        LOOKUP = Collections.unmodifiableMap(Arrays.stream(State.values())
                .collect(Collectors.toMap(State::getVal, Function.identity())));
    }

    private final short val;

    public static State lookup(short val){
        return Optional.ofNullable(LOOKUP.get(val)).orElseThrow(() ->new IllegalArgumentException("Unknown value " + val));
    }

}
