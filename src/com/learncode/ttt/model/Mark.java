package com.learncode.ttt.model;

/**
 * Created by Coen on 11-7-2017.
 */
public enum Mark {

    NOUGHT() {
        @Override
        public String toString() {
            return "O";
        }
    },
    CROSS() {
        @Override
        public String toString() {
            return "X";
        }
    },
    NONE() {
        @Override
        public String toString() {
            return " ";
        }
    };

    Mark() {

    }

    public Mark getOther() {
        if (this == NOUGHT) {
            return CROSS;
        } else if (this == CROSS) {
            return NOUGHT;
        } else {
            return NONE;
        }
    }

}
