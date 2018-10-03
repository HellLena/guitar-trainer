package Enums;

/**
 * Created by Елена on 30.05.2016.
 */
public enum Notes {
    A0(21), A0d(22), B0(23),
    C1(24), C1d(25), D1(26), D1d(27), E1(28), F1(29), F1d(30), G1(31), G1d(32), A1(33), A1d(34), B1(35),
    C2(36), C2d(37), D2(38), D2d(39), /* Guitar Start (1st fret) */E2(40), F2(41), F2d(42), G2(43), G2d(44), A2(45), A2d(46), B2(47),
    C3(48), C3d(49), D3(50), D3d(51), E3(52), F3(53), F3d(54), G3(55), G3d(56), A3(57), A3d(58), B3(59),
    C4(60), C4d(61), D4(62), D4d(63), E4(64), F4(65), F4d(66), G4(67), G4d(68), A4(69), A4d(70), B4(71),
    C5(72), C5d(73), D5(74), D5d(75), E5(76), F5(77), F5d(78), G5(79), G5d(80), A5(81), A5d(82), B5(83),
    C6(84), C6d(85), D6(86), D6d(87), E6(88)/* Guitar End (24th fret) */, F6(89), F6d(90), G6(91), G6d(92), A6(93), A6d(94), B6(95),
    C7(96), C7d(97), D7(98), D7d(99), E7(100),F7(101),F7d(102),G7(103),G7d(104),A7(105),A7d(106),B7(107),
    C8(108);
    private int midiNum;

    Notes(int midiNum) {
        this.midiNum = midiNum;
    }

    public int getMidiNum(){
        return this.midiNum;
    }
}
