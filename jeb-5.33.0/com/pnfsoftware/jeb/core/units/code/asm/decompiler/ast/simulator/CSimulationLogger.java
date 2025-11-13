package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.agk;
import java.util.ArrayList;
import java.util.List;

public class CSimulationLogger {
   private List stmtsFlatList;
   private List executionTrace;
   private CMethodState finalState;
   private CEnvironment finalEnvironment;

   public CSimulationLogger(CMethodSimulator var1) {
      this.stmtsFlatList = var1.getStmtsFlatList();
      this.executionTrace = new ArrayList();
   }

   public void logExecutedIndex(int var1) {
      this.executionTrace.add(var1);
   }

   public void setFinalState(CMethodState var1) {
      this.finalState = var1;
   }

   public CMethodState getFinalState() {
      return this.finalState;
   }

   public List getExecutionTrace() {
      return this.executionTrace;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("CSimulationResult:");
      var1.append(Strings.LINESEP);
      var1.append("   final state: ");
      var1.append(this.finalState);
      var1.append(Strings.LINESEP);
      var1.append("   ");
      var1.append("executionTrace: ");
      var1.append(Strings.LINESEP);

      for (Integer var3 : this.executionTrace) {
         var1.append("      ");
         var1.append(var3);
         var1.append(" ");
         ICStatement var4 = (ICStatement)this.finalState.getStatements().get(var3);
         if (!(var4 instanceof agk)) {
            var1.append(var4);
         }

         var1.append(Strings.LINESEP);
      }

      var1.append("   final environment:");
      var1.append(Strings.LINESEP);
      var1.append(this.finalEnvironment);
      return var1.toString();
   }

   public List getStmtsFlatList() {
      return this.stmtsFlatList;
   }

   public CEnvironment getFinalEnvironment() {
      return this.finalEnvironment;
   }

   public void setFinalEnvironment(CEnvironment var1) {
      this.finalEnvironment = var1;
   }
}
