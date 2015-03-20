package org.shared.chess;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.google.appengine.labs.repackaged.com.google.common.collect.Maps;

/** 
 * Uses a golden implementation to check other implementations.
 * 
 * @author yzibin@google.com (Yoav Zibin)
 */
public class RandomStateWalkTest {
  StateChanger goldenChanger = 
    new org.chenji.hw2.StateChangerImpl();
    //new org.yoavzibin.hw2.StateChangerImpl();
  StateChanger[] changers =
    new StateChanger[]{
      new org.alexanderoskotsky.hw2.StateChangerImpl(),
      new org.longjuntan.hw2.StateChangerImpl(),
      new org.shitianren.hw2.StateChangerImpl(),
      new org.kanwang.hw2.StateChangerImpl(),
      new org.kuangchelee.hw2.StateChangerImpl(),
      new org.vorasahil.hw2.StateChangerImpl(),
      new org.zhihanli.hw2.StateChangerImpl(),
      new org.paulsultan.hw2.StateChangerImpl(),
      new org.simongellis.hw2.StateChangerImpl(),
      new org.wenjiechen.hw2.StateChangerImpl(),
      new org.zhaohuizhang.hw2.StateChangerImpl(),
      new org.markanderson.hw2.StateChangerImpl(),
      new org.karthikmahadevan.hw2.StateChangerImpl(),
      new org.yuehlinchung.hw2.StateChangerImpl(),
      new org.bohuang.hw2.StateChangerImpl(),
      new org.haoxiangzuo.hw2.StateChangerImpl(),
      new org.corinnetaylor.hw2.StateChangerImpl(),
      new org.shihweihuang.hw2.StateChangerImpl(),
      new org.bohouli.hw2.StateChangerImpl(),
      new org.harshmehta.hw2.StateChangerImpl(),
      new org.ashishmanral.hw2.StateChangerImpl(),
      new org.yuanjia.hw2.StateChangerImpl(),
      new org.alishah.hw2.StateChangerImpl(),
      new org.leozis.hw2.StateChangerImpl(),
      new org.sanjana.hw2.StateChangerImpl(),
      new org.peigenyou.hw2.StateChangerImpl(),
      new org.mengyanhuang.hw2.StateChangerImpl(),
      new org.jiangfengchen.hw2.StateChangerImpl(),
      new org.adamjackrel.hw2.StateChangerImpl(),
      // 8-38: 31 implementations
  };
  StateExplorer goldenExplorer = 
    //new org.yoavzibin.hw2_5.StateExplorerImpl();
    new org.chenji.hw2_5.StateExplorerImpl();
  StateExplorer[] explorers =
    new StateExplorer[]{
      new org.alexanderoskotsky.hw2_5.StateExplorerImpl(),
      new org.longjuntan.hw2_5.StateExplorerImpl(),
      new org.shitianren.hw2_5.StateExplorerImpl(),
      new org.kanwang.hw2_5.StateExplorerImpl(),
      new org.kuangchelee.hw2_5.StateExplorerImpl(),
      new org.vorasahil.hw2_5.StateExplorerImpl(),
      new org.zhihanli.hw2_5.StateExplorerImpl(),
      new org.paulsultan.hw2_5.StateExplorerImpl(),
      new org.simongellis.hw2_5.StateExplorerImpl(),
      new org.wenjiechen.hw2_5.StateExplorerImpl(),
      new org.zhaohuizhang.hw2_5.StateExplorerImpl(),
      new org.markanderson.hw2_5.StateExplorerImpl(),
      new org.karthikmahadevan.hw2_5.StateExplorerImpl(),
      new org.yuehlinchung.hw2_5.StateExplorerImpl(),
      new org.bohuang.hw2_5.StateExplorerImpl(),
      new org.haoxiangzuo.hw2_5.StateExplorerImpl(),
      new org.corinnetaylor.hw2_5.StateExplorerImpl(),
      new org.shihweihuang.hw2_5.StateExplorerImpl(),
      new org.bohouli.hw2_5.StateExplorerImpl(),
      new org.harshmehta.hw2_5.StateExplorerImpl(),
      new org.ashishmanral.hw2_5.StateExplorerImpl(),
      new org.yuanjia.hw2_5.StateExplorerImpl(),
      new org.alishah.hw2_5.StateExplorerImpl(),
      new org.leozis.hw2_5.StateExplorerImpl(),
      //new org.sanjana.hw2_5.StateExplorerImpl(),
      new org.peigenyou.hw2_5.StateExplorerImpl(),
      new org.mengyanhuang.hw2_5.StateExplorerImpl(),
      new org.jiangfengchen.hw2_5.StateExplorerImpl(),
      //new org.adamjackrel.hw2_5.StateExplorerImpl(),
  };

  Map<String, String> errors = Maps.newLinkedHashMap();
  Map<String, Integer> errorCounts = Maps.newLinkedHashMap();
  
  private void addError(Class<?> xlass, String error) {
    if (error == null) return;
    String key = xlass.getName();
    errors.put(key, error);
    Integer oldCount = errorCounts.get(key);
    int newCount = oldCount == null ? 1 : oldCount+1;
    errorCounts.put(key, newCount);
  }
  
  private void checkExplorers(State state) {
    Set<Move> expectedPossibleMoves = 
      goldenExplorer.getPossibleMoves(state);
    Set<Position> expectedStartPositions = 
      goldenExplorer.getPossibleStartPositions(state);
    for (StateExplorer stateExplorer : explorers) {
      try {
        Set<Move> possibleMoves = 
          stateExplorer.getPossibleMoves(state);
        Set<Position> startPositions = 
          stateExplorer.getPossibleStartPositions(state);
        if (!possibleMoves.equals(expectedPossibleMoves)) {
          addError(stateExplorer.getClass(),
              "\n getPossibleMoves on state: " + state 
              + "\nExpected: " + expectedPossibleMoves 
              + "\nBut was: " + possibleMoves + "\n");
        }
        if (!startPositions.equals(expectedStartPositions)) {
          addError(stateExplorer.getClass(),
              "\n getPossibleStartPositions on state: " + state
              + "\nExpected: " + expectedStartPositions 
              + "\nBut was: " + startPositions + "\n");
        }
        for (Position start : expectedStartPositions) {
          Set<Move> expectedPossibleMovesFrom = 
              goldenExplorer.getPossibleMovesFromPosition(state, start);
          Set<Move> possibleMovesFrom = 
              stateExplorer.getPossibleMovesFromPosition(state, start);
          if (!possibleMovesFrom.equals(expectedPossibleMovesFrom)) {
            addError(stateExplorer.getClass(),
                "\n getPossibleMovesFromPosition on state: " + state
                + "\nExpected: " + expectedPossibleMovesFrom 
                + "\nBut was: " + possibleMovesFrom + "\n");
          }
        }
      } catch (Exception e) {
        addError(stateExplorer.getClass(),
            "\nExplorer on state: " + state
            + "\nHad an error: " + e.toString() + "\n");
      }
    }
  }

  private void checkChangers(State state, Move randomMove) {
    State from = state.copy();
    State to = state.copy();
    goldenChanger.makeMove(to, randomMove);
    for (StateChanger stateChanger : changers) {
      State newState = from.copy();
      String outcome = null;
      try {
        stateChanger.makeMove(newState, randomMove);
        if (!to.equals(newState)) {
          outcome = "\n makeMove on state: " + from + "\n and move: " + randomMove
              + "\nExpected: " + to 
              + "\nBut was: " + newState + "\n";
        }
      } catch (Exception e) {
        outcome = "\n makeMove on state: " + from + "\n and move: " + randomMove
            + "\nExpected: " + to 
            + "\nBut had an error: " + e.toString() + "\n";
      }
      addError(stateChanger.getClass(), outcome);
    }
  }
  
  @Test
  public void testWithRandomWalk() {
    Random random = new Random(42L);
    for (int matchesNum = 0; matchesNum < 10; matchesNum++) {
      State state = new State();
      while (state.getGameResult() == null) {
        checkExplorers(state.copy());
        Set<Move> possibleMoves = goldenExplorer.getPossibleMoves(state);
        Move[] movesArray = possibleMoves.toArray(new Move[0]);
        Arrays.sort(movesArray, new Comparator<Move>() {
          @Override
          public int compare(Move o1, Move o2) {
            return o1.toString().compareTo(o2.toString());
          }
        });
        Move randomMove = movesArray[random.nextInt(movesArray.length)];
        checkChangers(state, randomMove);
        goldenChanger.makeMove(state, randomMove);
      }
    }
    assertTrue("Counts=" + errorCounts + "\n" + errors.toString(), errors.size() == 0);
  }
}
