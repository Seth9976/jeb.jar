package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aco;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CMethodState {
   private static final StructuredLogger logger = aco.pC(CMethodState.class);
   private final List statements;
   private CMethodState.ControlWord controlWord;
   private int controlWordIndex;
   private Map localVarValues = new HashMap();
   private Long returnValue;
   private boolean methodParametersSpoiled = true;

   public CMethodState(List var1) {
      this.statements = var1;
   }

   public void setValue(ICElement var1, Long var2, CEnvironment var3) {
      ICIdentifier var4 = this.getIdentifier(var1);
      if (var4 != null) {
         if (var4.getIdentifierClass().isLocal()) {
            this.localVarValues.put(var4, var2);
         } else {
            if (!var4.getIdentifierClass().isGlobal()) {
               throw new CSimulationException(Strings.ff("cannot set value (%s)", var1));
            }

            var3.setGlobalVarValue(var4, var2);
         }
      } else {
         throw new CSimulationException(Strings.ff("cannot set value (%s)", var1));
      }
   }

   public void setLocalVarValue(ICIdentifier var1, Long var2) {
      this.localVarValues.put(var1, var2);
   }

   public ICIdentifier getIdentifier(ICElement var1) {
      if (var1 instanceof ICIdentifier) {
         return (ICIdentifier)var1;
      } else {
         return var1 instanceof ICDecl ? ((ICDecl)var1).getIdentifier() : null;
      }
   }

   public Long getLocalVarValue(ICElement var1) {
      return (Long)this.localVarValues.get(var1);
   }

   public List getStatements() {
      return this.statements;
   }

   public static boolean areEquivalent(CMethodState var0, CMethodState var1, boolean var2, boolean var3) {
      if (!areReturnValuesTheSame(var0, var1)) {
         return false;
      } else {
         return var2 && !areSameValuesProduced(var0, var1) ? false : !var3 || areControlWordsTheSame(var0, var1);
      }
   }

   private static boolean areControlWordsTheSame(CMethodState var0, CMethodState var1) {
      return var0.getControlWord().equals(var1.getControlWord());
   }

   private static boolean areReturnValuesTheSame(CMethodState var0, CMethodState var1) {
      return var0.getReturnValue() == null && var1.getReturnValue() == null
         || var0.getReturnValue() != null && var0.getReturnValue().equals(var1.getReturnValue());
   }

   private static boolean areSameValuesProduced(CMethodState var0, CMethodState var1) {
      HashSet var2 = new HashSet(var0.getLocalVarValues().values());
      var2.add(var0.getReturnValue());
      HashSet var3 = new HashSet(var1.getLocalVarValues().values());
      var3.add(var1.getReturnValue());
      return var2.equals(var3);
   }

   public CMethodState.ControlWord getControlWord() {
      return this.controlWord;
   }

   public void setControlWord(CMethodState.ControlWord var1) {
      this.controlWord = var1;
   }

   @Override
   public String toString() {
      return "CMethodState [controlWord=" + this.controlWord + ", values=" + this.localVarValues + ", ret_val=" + this.returnValue + "]";
   }

   public Long getReturnValue() {
      return this.returnValue;
   }

   public void setReturnValue(Long var1) {
      this.returnValue = var1;
   }

   public Map getLocalVarValues() {
      return this.localVarValues;
   }

   public int getControlWordIndex() {
      return this.controlWordIndex;
   }

   public void setControlWordIndex(int var1) {
      this.controlWordIndex = var1;
   }

   public boolean isMethodParametersSpoiled() {
      return this.methodParametersSpoiled;
   }

   public void setMethodParametersSpoiled(boolean var1) {
      this.methodParametersSpoiled = var1;
   }

   public static enum ControlWord {
      GOTO_NEXT_INS,
      GOTO_END_OF_METHOD,
      SKIP_NEXT_BLOCK,
      GOTO_PREV_BLOCK,
      GOTO_INDEX;
   }
}
