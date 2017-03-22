package refactoring_guru.patterns.adapter.example;

import refactoring_guru.patterns.adapter.example.adapters.SquarePegAdapter;
import refactoring_guru.patterns.adapter.example.round.RoundHole;
import refactoring_guru.patterns.adapter.example.round.RoundPeg;
import refactoring_guru.patterns.adapter.example.square.SquarePeg;

/**
 * EN: Somewhere in client code...
 *
 * RU: Где-то в клиентском коде...
 */
public class Demo {
    public static void main(String[] args) {
        // EN: Round fits round, no surprise.
        //
        // RU: Круглое к круглому — всё работает.
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        // EN: This won't compile (incompatible types):
        // hole.fits(smallSqPeg);
        // hole.fits(largeSqPeg);
        //
        // RU: Но это не будет компилироваться из-за ошибки типов:
        // hole.fits(smallSqPeg);
        // hole.fits(largeSqPeg);

        // EN: Adapter solves the problem.
        //
        // RU: Адаптер решит проблему.
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}
