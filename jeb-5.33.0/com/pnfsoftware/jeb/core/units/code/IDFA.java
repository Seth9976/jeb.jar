package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.Collection;
import java.util.Map;

public interface IDFA {
   int STANDARD_COLLECTION_FLAGS = 3;
   boolean STANDARD_INTEGRATE_INPUTS = true;

   IControlFlowGraph getCfg();

   void setVariableCollectionFlags(int var1);

   int getVariableCollectionFlags();

   default void setConservative() {
      this.setVariableCollectionFlags(3);
   }

   default boolean isConservative() {
      return this.getVariableCollectionFlags() == 3;
   }

   boolean setIntegrateCalculatedInputRegisters(boolean var1);

   boolean isIntegrateCalculatedInputRegisters();

   boolean setAlwaysExamineIrregularFlows(boolean var1);

   boolean isAlwaysExamineIrregularFlows();

   ICFGOwnerContext setVariableInformationProvider(ICFGOwnerContext var1);

   ICFGOwnerContext getVariableInformationProvider();

   int setMaxBlocks(int var1);

   int getMaxBlocks();

   void perform();

   boolean isValid();

   void invalidate();

   void notifyInstructionUpdate(long var1);

   void invalidateForRemoval(long var1);

   void invalidateForSubstitution(long var1, Collection var3, Collection var4);

   void invalidatePostSimpleSubstitutionWithMultiDefs(Collection var1, long var2, int var4);

   void collectInstructionAllDefs(long var1, Collection var3);

   void collectInstructionAllUses(long var1, Collection var3);

   Collection getInstructionAllDefs(long var1);

   Collection getInstructionAllUses(long var1);

   Collection getInstructionDefs(long var1);

   Collection getInstructionUses(long var1);

   Collection getInstructionPotentialDefs(long var1);

   Collection getInstructionPotentialUses(long var1);

   Collection getInstructionSpoiledDefs(long var1);

   DUI getDUI(long var1);

   DUI getDUI(AddressableInstruction var1);

   DUI getDUI(long var1, IInstruction var3);

   boolean isTerminator(IBasicBlock var1);

   Collection getInputs();

   Map getInputMap();

   Collection getInputMap(int var1);

   Collection getOutputs(IBasicBlock var1);

   Map getOutputMap(IBasicBlock var1);

   Collection getOutputMap(IBasicBlock var1, int var2);

   Collection getOutputs();

   Map getOutputMap();

   Collection getOutputMap(int var1);

   Map getUseDefChains(long var1);

   Map getDefUseChains(long var1);

   Collection getUseDefs(long var1, int var3);

   Collection getUseDefs(long var1, int var3, int var4);

   Collection getBlockUseDefs(long var1, int var3);

   Collection getDefUses(long var1, int var3);

   Collection getDefUses(long var1, int var3, int var4);

   Collection getDefUses(long var1, int var3, int var4, boolean var5);

   Collection getBlockDefUses(long var1, int var3);

   Collection getBlockDefUses(long var1, int var3, int var4);

   Collection getLiveChains(IBasicBlock var1, int var2, int var3);

   Collection getLiveChains(IBasicBlock var1, int var2, int var3, int var4);

   Map getLiveChains(IBasicBlock var1, int var2);

   boolean isAlive(IBasicBlock var1, int var2, int var3);

   Collection getReachChains(IBasicBlock var1, int var2, int var3);

   Collection getReachChains(IBasicBlock var1, int var2, int var3, int var4);

   Map getReachChains(IBasicBlock var1, int var2);

   boolean isReaching(IBasicBlock var1, int var2, int var3);

   Collection getUseDiscrepancies(long var1);

   boolean isUseDiscrepancy(long var1, int var3);

   int checkNoUse(long var1, int var3, boolean var4, int var5);

   boolean checkNoUse(long var1, int var3, boolean var4);

   boolean checkNoUse(long var1, int var3);

   Long checkSingleUse(long var1, int var3);

   default boolean checkSingleUse(long var1, int var3, long var4) {
      return Longs.equals(this.checkSingleUse(var1, var3), var4);
   }

   Long checkSingleDefNoInput(long var1, int var3);

   Long checkSingleDef(long var1, int var3);

   default boolean checkSingleDef(long var1, int var3, long var4) {
      return Longs.equals(this.checkSingleDef(var1, var3), var4);
   }

   Long checkSingleSource(long var1, int var3, boolean var4);

   Long checkSingleSource(long var1, int var3);

   default boolean checkSingleSource(long var1, int var3, long var4) {
      return Longs.equals(this.checkSingleSource(var1, var3), var4);
   }

   Boolean isVarReachingFromTo(int var1, long var2, long var4);

   Boolean isVarReachingFromTo(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5);

   Boolean isVarReachingFromTo(int var1, IBasicBlock var2, int var3, IBasicBlock var4, int var5, boolean var6, int var7);
}
