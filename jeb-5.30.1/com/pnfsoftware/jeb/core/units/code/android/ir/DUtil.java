package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.EffectiveFinalityType;
import com.pnfsoftware.jeb.core.units.code.android.IDexContextInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.primitives.Integers;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DUtil {
   public static int createRegisterVarId(int var0, boolean var1) {
      if (var1) {
         Assert.a(var0 >= 0 && var0 < 65535);
         return 65536 + var0;
      } else {
         Assert.a(var0 >= 0 && var0 < 65536);
         return var0;
      }
   }

   public static boolean isRegisterVarId(int var0) {
      if (var0 >= 0) {
         if (var0 <= 65535) {
            return true;
         }

         if (var0 >= 65536 && var0 <= 131070) {
            return true;
         }
      }

      return false;
   }

   public static boolean isVirtualVarId(int var0) {
      if (var0 >= 0) {
         if (var0 >= 131072 && var0 <= 589823) {
            return true;
         }

         if (var0 >= 589824 && var0 <= 1048574) {
            return true;
         }
      }

      return false;
   }

   public static boolean isLegalVarId(int var0) {
      return isSingleSlotVarId(var0) || isDoubleSlotVarId(var0);
   }

   public static boolean isSingleSlotVarId(int var0) {
      if (var0 >= 0) {
         if (var0 <= 65535) {
            return true;
         }

         if (var0 >= 131072 && var0 <= 589823) {
            return true;
         }
      }

      return false;
   }

   public static boolean isDoubleSlotVarId(int var0) {
      if (var0 >= 0) {
         if (var0 >= 65536 && var0 <= 131070) {
            return true;
         }

         if (var0 >= 589824 && var0 <= 1048574) {
            return true;
         }
      }

      return false;
   }

   public static String formatVarId(int var0) {
      if (var0 >= 0 && var0 < 65536) {
         return "v" + var0;
      } else if (var0 >= 65536 && var0 < 131071) {
         return "V" + (var0 - 65536);
      } else if (var0 >= 131072 && var0 < 589824) {
         return "x" + (var0 - 131072);
      } else if (var0 >= 589824 && var0 < 1048575) {
         return "X" + (var0 - 589824);
      } else {
         throw new IllegalArgumentException(Strings.ff("Illegal variable id: 0x%X", var0));
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public static int parseVarIdFromStandardName(String var0, boolean var1) {
      label51: {
         char var2;
         int var3;
         try {
            var2 = var0.charAt(0);
            var3 = Integer.parseInt(var0.substring(1));
            if (var2 == 'v') {
               if (var3 >= 0 && var3 < 65536) {
                  return var3;
               }
               break label51;
            }
         } catch (Exception var5) {
            if (var1) {
               return -1;
            }

            throw new IllegalArgumentException("Illegal variable name: " + var0);
         }

         if (var2 == 'V') {
            if (var3 >= 0 && var3 < 65535) {
               return 65536 + var3;
            }
         } else if (var2 == 'x') {
            if (var3 >= 0 && var3 < 458752) {
               return 131072 + var3;
            }
         } else if (var2 == 'X' && var3 >= 0 && var3 < 458751) {
            return 589824 + var3;
         }
      }

      try {
         throw new RuntimeException();
      } catch (Exception var4) {
         if (var1) {
            return -1;
         } else {
            throw new IllegalArgumentException("Illegal variable name: " + var0);
         }
      }
   }

   public static String formatVarIds(Collection var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (int var4 : var0) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var1.append(formatVarId(var4));
         var2++;
      }

      return var1.toString();
   }

   public static boolean replaceInExpression(IDExpression var0, IDExpression var1, IDExpression var2) {
      if (var0 == null || var1 == null || var2 == null) {
         throw new IllegalArgumentException();
      } else {
         return var0 == var1 ? false : !var0.visitDepthPre(new DUtil$1(var1, var2));
      }
   }

   public static int countVariable(IDExpression var0, IDVar var1) {
      DUtil$1V var2 = new DUtil$1V(var1);
      var0.visitDepthPost(var2);
      return var2.cnt;
   }

   public static boolean usesReferences(IDExpression var0) {
      return !var0.visitDepthPost(new DUtil$2());
   }

   public static List collectVars(IDExpression var0) {
      ArrayList var1 = new ArrayList();
      var0.visitDepthPost(new DUtil$3(var1));
      return var1;
   }

   public static Set collectUniqueVarIds(IDExpression var0) {
      HashSet var1 = new HashSet();
      var0.visitDepthPost(new DUtil$4(var1));
      return var1;
   }

   public static int calculateComplexity(IDExpression var0) {
      int var1 = 1;
      ArrayList var2 = new ArrayList();
      var0.collectSubExpressions(var2);

      for (IDExpression var4 : var2) {
         var1 += calculateComplexity(var4);
      }

      return var1;
   }

   public static boolean hasInvokeInfo(IDExpression var0) {
      return !var0.visitDepthPost(new DUtil$5());
   }

   public static boolean hasVariables(IDExpression var0) {
      return var0 instanceof IDVar || !var0.visitDepthPre(new DUtil$6());
   }

   public static boolean hasVariable(IDExpression var0, int var1) {
      return var0.isVar(var1) || !var0.visitDepthPre(new DUtil$7(var1));
   }

   public static boolean checkBlock(BasicBlock var0, DOpcodeType... var1) {
      if (var0.size() == var1.length) {
         for (int var2 = 0; var2 < var1.length; var2++) {
            if (((IDInstruction)var0.get(var2)).getOpcode() != var1[var2]) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static int checkSequence(CFG var0, int var1, DOpcodeType... var2) {
      int var3 = var2.length;
      int var4 = 0;
      BasicBlock var5 = var0.get(var1);

      for (int var6 = 0; var5 != null; var5 = var0.get(var1)) {
         int var7;
         for (var7 = 0; var7 < var5.size() && var3 > 0; var4++) {
            if (((IDInstruction)var5.get(var7)).getOpcode() != var2[var4]) {
               return 0;
            }

            var3--;
            var7++;
         }

         var6++;
         if (var3 == 0) {
            if (var7 == var5.size()) {
               return var6;
            }

            return 0;
         }

         if (var5.outsize() != 1) {
            return 0;
         }

         if (++var1 >= var0.size()) {
            return 0;
         }
      }

      return 0;
   }

   public static boolean mayBeFinal(IDField var0, IDexUnit var1) {
      if (var0.getIndex() == null) {
         return true;
      } else {
         IDexField var2 = var1.getField(var0.getIndex().getValue());
         if (var2.isInternal()) {
            return var2.getData().isFinal();
         } else {
            String var3 = var2.getSignature(false);
            EffectiveFinalityType var4 = var1.getContextInfoProvider().getFieldEFInfo(var3);
            return var4.isFinalLike();
         }
      }
   }

   public static int cleanGraph(IDMethodContext var0) {
      int var1 = 0;
      var1 += removeUnreachableBlocks(var0);
      return var1 + simplifyJCondsAndSwitches(var0);
   }

   public static int removeGaps(CFG var0) {
      int var1 = 0;
      List var2 = var0.getBlocksView();
      IBasicBlock var3 = (IBasicBlock)var2.get(0);

      for (int var4 = 1; var4 < var2.size(); var4++) {
         IDInstruction var5 = (IDInstruction)var3.getLast();
         IBasicBlock var6 = (IBasicBlock)var2.get(var4);
         IDInstruction var7 = (IDInstruction)var6.get(0);
         int var8 = (int)(var7.getOffset() - var5.getOffsetEnd());
         if (var8 != 0) {
            var5.adjustSize(var8);
            var1++;
         }

         var3 = var6;
      }

      return var1;
   }

   public static int removeUnreachableBlocks(IDMethodContext var0) {
      return removeUnreachableBlocks(var0.getCfg(), var0.getExceptionData());
   }

   public static int removeUnreachableBlocks(CFG var0, IDTryData var1) {
      LinkedList var2 = new LinkedList(var0.getBlocksView());
      var2.removeAll(getReachableBlocks(var0));
      return var2.isEmpty() ? 0 : removeUnreachableBlocks(var2, var0, var1);
   }

   public static Collection getReachableBlocks(CFG var0) {
      HashSet var1 = new HashSet();
      ArrayDeque var2 = new ArrayDeque();
      var2.add(var0.get(0));

      while (!var2.isEmpty()) {
         BasicBlock var3 = (BasicBlock)var2.remove();
         if (var1.add(var3)) {
            var2.addAll(var3.getOutputs());
            var2.addAll(var3.getIrregularOutputs());
         }
      }

      return var1;
   }

   private static int removeUnreachableBlocks(Deque var0, CFG var1, IDTryData var2) {
      HashSet var3 = new HashSet();

      while (!var0.isEmpty()) {
         BasicBlock var4 = (BasicBlock)var0.remove();
         long var5 = var4.getAddress();
         if (var5 != 0L && var3.add(var5)) {
            List var7 = var4.getAllOutputBlocks();
            var1.deleteOutEdges(var4);
            var1.deleteIrregularOutEdges(var4);
            if (var2 != null) {
               var2.unprotectBlock((int)var4.getAddress());
            }

            for (BasicBlock var9 : var7) {
               if (var9.allinsize() == 0) {
                  var0.add(var9);
               }
            }
         }
      }

      for (long var11 : var3) {
         BasicBlock var12 = var1.getBlockAt(var11);
         var1.removeBlock(var12);
         BasicBlock var13 = var1.getBlockEndingAt(var12.getFirstAddress());
         ((IDInstruction)var13.getLast()).adjustSize(var12.getSizeOfInstructions());
      }

      return var3.size();
   }

   public static int simplifyJCondsAndSwitches(IDMethodContext var0) {
      CFG var1 = var0.getCfg();
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size() - 1; var3++) {
         BasicBlock var4 = var1.get(var3);
         IDInstruction var5 = (IDInstruction)var4.getLast();
         switch (var5.getOpcode()) {
            case IR_JCOND:
               if (var5.getBranchTarget() == var5.getOffsetEnd()) {
                  IDExpression var9 = var5.getJcondCondition();
                  if (var9.hasSideEffects(var0, true)) {
                     IJavaType var10 = var9.getType() != null ? var9.getType() : var0.getTypeFactory().getBoolean();
                     IDVar var11 = var0.createVirtualVar(var10);
                     var5.transformJcondToAssign(var11);
                  } else {
                     var5.transformToNop();
                  }

                  var1.deleteOutEdges(var4);
                  var1.addEdge(var4, var1.get(var3 + 1));
                  var2++;
               }
               break;
            case IR_SWITCH:
               IDSwitchData var6 = var5.getSwitchData();
               if (var6.deleteCasesTo((int)var5.getOffsetEnd()) > 0) {
                  var1.deleteOutEdges(var4);
                  var1.addEdge(var4, var1.get(var3 + 1));

                  for (IDTarget var8 : var6.getTargets(true)) {
                     var1.addEdge(var4, var1.getBlockAt((long)var8.getOffset()));
                  }

                  var2++;
               }
         }
      }

      return var2;
   }

   public static String getExceptionSignature(IDMethodContext var0, IDExceptionHandler var1) {
      int var2 = var1.getTypeIndex();
      if (var2 < 0) {
         return "Ljava/lang/Throwable;";
      } else {
         IDexType var3 = var0.getDex().getType(var2);
         return var3 == null ? null : var3.getSignature(false);
      }
   }

   public static boolean canHandlerCatchException(IDMethodContext var0, IDExceptionHandler var1, String var2) {
      if (var1.isCatchAll(var0)) {
         return true;
      } else {
         String var3 = getExceptionSignature(var0, var1);
         return var3 == null ? false : var0.getTypeInfoProvider().isCompatible(var2, var3);
      }
   }

   public static boolean unprotectBlock(CFG var0, IDTryData var1, int var2) {
      BasicBlock var3 = var0.getBlockAt((long)var2);
      if (var3 == null) {
         throw new IllegalArgumentException();
      } else if (!var1.unprotectBlock(var2)) {
         return false;
      } else {
         var0.deleteIrregularOutEdges(var3);
         removeUnreachableBlocks(var0, var1);
         return true;
      }
   }

   public static ContextAccessType getCAT(IDInstruction var0, boolean var1) {
      IDexContextInfoProvider var2 = var0.getContext().getDex().getContextInfoProvider();
      DUtil.CATDeterminer var3 = new DUtil.CATDeterminer(var2);
      var0.visitInstructionPostOrder(var3, true);
      if (var1 && var0.getOpcode() == DOpcodeType.IR_ASSIGN && !var3.cat.isAllAccess()) {
         IDExpression var4 = var0.getAssignDestination();
         if (var4 instanceof IDArrayElt) {
            var3.cat = var3.cat.addAccess(ContextAccessType.WRITE_ONLY);
            if (!var3.cat.isAllAccess()) {
               IDExpression var5 = ((IDArrayElt)var4).getArray();
               var5.visitDepthPost(var3);
               if (!var3.cat.isAllAccess()) {
                  var5 = ((IDArrayElt)var4).getIndex();
                  var5.visitDepthPost(var3);
               }
            }
         } else if (var4 instanceof IDInstanceField) {
            var3.cat = var3.cat.addAccess(ContextAccessType.WRITE_ONLY);
            if (!var3.cat.isAllAccess()) {
               IDExpression var7 = ((IDInstanceField)var4).getInstance();
               var7.visitDepthPost(var3);
            }
         } else if (var4 instanceof IDStaticField) {
            var3.cat = var3.cat.addAccess(ContextAccessType.WRITE_ONLY);
         }
      }

      return var3.cat;
   }

   public static boolean isUsingCaughtException(IDMethodContext var0, BasicBlock var1) {
      int var2 = 0;

      while (var2 < var1.size() && ((IDInstruction)var1.get(var2)).getOpcode() == DOpcodeType.IR_NOP) {
         var2++;
      }

      if (var2 >= var1.size()) {
         return true;
      } else {
         IDInstruction var3 = (IDInstruction)var1.get(var2);
         if (var3.getOpcode() != DOpcodeType.IR_STORE_EXCEPTION) {
            return false;
         } else {
            IDVar var4 = (IDVar)var3.getOperand1();
            IDFA var5 = var0.getCfg().doDataFlowAnalysis(true);
            return !var5.checkNoUse(var3.getOffset(), var4.getId());
         }
      }
   }

   public static String generateNativeAddress(IDMethodContext var0, IDExpression var1) {
      String var2 = var0.getMethodSignature();
      if (var1 == null) {
         return var2;
      } else {
         int var3 = var1.getPhysicalOffset();
         if (var3 < 0) {
            var3 = 0;
         }

         return Strings.ff("%s+%Xh", var2, var3);
      }
   }

   public static ICodeCoordinates generateNativeCoordinates(IDMethodContext var0, IDExpression var1) {
      int var2 = var0.getMethod().getIndex();
      if (var1 == null) {
         return new MethodCoordinates(var2);
      } else {
         int var3 = var1.getPhysicalOffset();
         if (var3 < 0) {
            var3 = 0;
         }

         return new InstructionCoordinates(var2, var3);
      }
   }

   public static void verifyGraph(IDMethodContext var0) {
      verifyGraph(var0, var0.getMethodSignature());
   }

   public static void verifyGraph(IDMethodContext var0, String var1) {
      verifyGraph(var0, "failed", var1);
   }

   public static void verifyGraph(IDMethodContext var0, String var1, String var2) {
      verifyGraph(var0, var0.getCfg(), var0.getExceptionData(), var1, var2);
   }

   public static void verifyGraph(IDMethodContext var0, CFG var1, IDTryData var2, String var3, String var4) {
      try {
         verifyInstructions(var1, var0);
         verifyUnicity(var1);
         CFGUtil.verify(var1, true, true, true, true, false);
         verifyBasicConsistency(var1);
         if (var2 != null) {
            verifyProtectedBlocks(var1, var2);
         }
      } catch (RuntimeException var7) {
         if (var3 != null) {
            String var6 = "INFO: " + var4 + "\nERROR: " + var7.getMessage();
            dump(var1, var3, var6);
         }

         throw var7;
      }
   }

   static void verifyInstructions(CFG var0, IDMethodContext var1) {
      long var2 = var0.getFirstAddress();

      for (IDInstruction var5 : var0.instructions()) {
         var5.verify();
         if (var1 != null && var5.getContext() != var1) {
            throw new IllegalStateException(Strings.ff("Unexpected context in instruction: 0x%X: %s", var5.getOffset(), var5));
         }

         if (var5.getOffset() < var2) {
            throw new IllegalStateException("Instruction is overlapping the previous instruction: " + var5);
         }

         if (var5.getSize() < 0) {
            throw new IllegalStateException("Instruction cannot have a 0 or negative size: " + var5);
         }

         long var6 = var5.getOffset() + var5.getSize();
         if (var5.getOffsetEnd() != var6) {
            throw new IllegalStateException("Instruction size inconsistency: " + var5);
         }

         var2 = var6;
      }
   }

   public static int verifyUnicity(CFG var0) {
      IdentityHashSet var1 = new IdentityHashSet();

      for (IDInstruction var3 : var0.instructions()) {
         verifyUnicity(var3, var1);
      }

      return var1.size();
   }

   public static int verifyUnicity(IDExpression var0) {
      IdentityHashSet var1 = new IdentityHashSet();
      return verifyUnicity(var0, var1);
   }

   private static int verifyUnicity(IDExpression var0, Set var1) {
      ArrayDeque var2 = new ArrayDeque();
      var2.add(var0);

      while (!var2.isEmpty()) {
         IDExpression var3 = (IDExpression)var2.remove();
         if (!(var3 instanceof IDVar) && !var1.add(var3)) {
            throw new IllegalStateException(Strings.ff("Duplicate found: %s (type:%s) in: %s", var3, var3.getClass().getSimpleName(), var0));
         }

         if (!(var3 instanceof IDNewArrayInfo var4 && var4.areSubExpsAllImms())) {
            var3.collectSubExpressions(var2);
         }
      }

      return var1.size();
   }

   static void verifyBasicConsistency(CFG var0) {
      LinkedHashSet var1 = new LinkedHashSet(var0.getBlocksView());
      if (var1.size() != var0.size()) {
         throw new IllegalArgumentException("Duplicate blocks were found");
      } else {
         for (BasicBlock var3 : var0) {
            if (var3.isEmpty()) {
               throw new IllegalArgumentException(Strings.ff("Empty block! %s", var3));
            }
         }

         for (BasicBlock var17 : var0) {
            for (BasicBlock var5 : var17.getInputs()) {
               if (!var1.contains(var5)) {
                  throw new IllegalArgumentException(Strings.ff("Input block not part of graph! %s", var5));
               }
            }

            for (BasicBlock var23 : var17.getOutputs()) {
               if (!var1.contains(var23)) {
                  throw new IllegalArgumentException(Strings.ff("Output block not part of graph! %s", var23));
               }
            }

            for (BasicBlock var24 : var17.getIrregularInputs()) {
               if (!var1.contains(var24)) {
                  throw new IllegalArgumentException(Strings.ff("Irr.input block not part of graph! %s", var24));
               }
            }

            for (BasicBlock var25 : var17.getIrregularOutputs()) {
               if (!var1.contains(var25)) {
                  throw new IllegalArgumentException(Strings.ff("Irr.output block not part of graph! %s", var25));
               }
            }
         }

         for (BasicBlock var18 : var0) {
            IDInstruction var22 = (IDInstruction)var18.getLast();
            int var26 = var18.outsize();
            long var6 = var18.getAddress();
            IFlowInformation var8 = var22.getBreakingFlow();
            if (!var8.isBroken()) {
               long var9 = var18.getEndAddress();
               if (var26 != 1) {
                  throw new IllegalArgumentException(Strings.ff("Block 0x%X: Expected a single fall-through block at 0x%X", var6, var9));
               }

               if (var18.getOutputBlock(0).getAddress() != var9) {
                  throw new IllegalArgumentException(
                     Strings.ff("Block 0x%X: The FT block is at 0x%X, whereas it should be at 0x%X", var6, var18.getOutputBlock(0).getAddress(), var9)
                  );
               }
            } else {
               if (var26 != var8.getTargets().size()) {
                  throw new IllegalArgumentException(Strings.ff("Block 0x%X: Found %d out-edges, expected %d", var6, var26, var8.getTargets().size()));
               }

               if (var26 > 0) {
                  long var27 = (Long)var18.getOutputOffsets().get(0);
                  long var11 = ((ICodePointer)var8.getTargets().get(0)).getAddress();
                  if (var27 != var11) {
                     throw new IllegalArgumentException(Strings.ff("Block 0x%X: Unexpected target: 0x%X / 0x%X", var6, var27, var11));
                  }

                  HashSet var13 = new HashSet(var18.getOutputOffsets());
                  Set var14 = (Set)var8.getTargets().stream().map(var0x -> var0x.getAddress()).collect(Collectors.toSet());
                  if (!var13.equals(var14)) {
                     throw new IllegalArgumentException(
                        Strings.ff("Block 0x%X: Unexpected targets: %s / %s", var6, Longs.formatHexCollection(var13), Longs.formatHexCollection(var14))
                     );
                  }
               }
            }
         }
      }
   }

   static void verifyProtectedBlocks(CFG var0, IDTryData var1) throws IllegalArgumentException {
      var1.isEmpty();
      List var2 = var1.getProtectedBlocks();

      for (BasicBlock var4 : var0) {
         int var5 = (int)var4.getAddress();
         var2.remove(Integer.valueOf(var5));
         List var6 = var1.getBlockHandlers(var5);
         List var7 = var4.getIrregularOutputOffsets();
         if (var6.size() != var7.size()) {
            throw new IllegalArgumentException(Strings.ff("Ex: Block 0x%X: Mismatched count: handlers:%d, irredges:%d", var5, var6.size(), var7.size()));
         }

         int var8 = 0;

         for (IDExceptionHandler var10 : var6) {
            int var11 = ((Long)var7.get(var8)).intValue();
            if (var10.getAddress() != var11) {
               throw new IllegalArgumentException(Strings.ff("Ex: Block 0x%X: Mismatched address: 0x%X, 0x%X", var5, var10.getAddress(), var11));
            }

            var8++;
         }
      }

      if (!var2.isEmpty()) {
         throw new IllegalArgumentException(Strings.ff("Ex: Protected block(s) not reflected in CFG: %s", Integers.formatHexIntegerCollection(var2)));
      }
   }

   public static void verifyDataFlow(CFG var0) throws IllegalArgumentException {
      IDFA var1 = var0.createDataFlowAnalysisHelperObject();
      var1.setAlwaysExamineIrregularFlows(true);

      for (BasicBlock var3 : var0) {
         Set var4 = var1.getLiveChains(var3, 0).keySet();

         for (BasicBlock var6 : var3.getAllInputs()) {
            int var7 = var6.size();
            if (var6.outsize() == 0) {
               var7--;
            }

            Set var8 = var1.getReachChains(var6, var7).keySet();
            if (!var8.containsAll(var4)) {
               throw new IllegalArgumentException(
                  Strings.ff("Data flow inconsistency: at block %s: not all live vars=%s are reaching from input %s: vars=%s", var3, var4, var6, var8)
               );
            }
         }
      }
   }

   public static void dump(CFG var0, String var1) {
      dump(var0, var1, null);
   }

   public static void dump(CFG var0, String var1, String var2) {
      try {
         if (var1 == null || var1.isEmpty()) {
            throw new RuntimeException("Provide a name!");
         }

         if (!var1.endsWith(".dot")) {
            var1 = var1 + ".dot";
         }

         IDMethodContext var3 = ((IDInstruction)var0.get(0).get(0)).getContext();
         IDTryData var4 = var3.getExceptionData();
         DUtil$8 var5 = new DUtil$8(var0, var4, var3);
         var5.setLineLimit(-60);
         var5.setTitle(var2);
         var5.setGenerateBlockIndices(0);
         String var6 = var5.generate();
         File var7 = IO.createTempFile(IO.sanitizePathUnsafe(var1));
         IO.writeFile(var7, Strings.encodeUTF8(var6));
      } catch (IOException var8) {
      }
   }

   public static boolean removeInstruction(BasicBlock var0, int var1) {
      IDInstruction var2 = (IDInstruction)var0.get(var1);
      if (var2 == null) {
         throw new RuntimeException();
      } else {
         IDMethodContext var3 = var2.getContext();
         if (var0.size() >= 2) {
            int var4 = var2.getSize();
            IDInstruction var5;
            if (var1 == 0) {
               var5 = (IDInstruction)var0.get(1);
               var5.setOffset(var2.getOffset());
            } else {
               var5 = (IDInstruction)var0.get(var1 - 1);
            }

            var5.adjustSize(var4);
         }

         var0.remove(var1);
         if (!var0.isEmpty()) {
            return true;
         } else if (var0.outsize() != 1) {
            throw new RuntimeException();
         } else {
            int var6 = (int)var0.getOutputBlock(0).getBase();
            IDInstruction var7 = var3.createJump(var6);
            var7.setOffset(var2.getOffset());
            var7.setSize(var2.getSize());
            var0.add(var7);
            return false;
         }
      }
   }

   public static boolean removeUnreachableTrampoline(CFG var0, BasicBlock var1) {
      if (var1.size() != 1 && !((IDInstruction)var1.get(0)).isJumpOrJcond()) {
         throw new IllegalArgumentException("Not a trampoline");
      } else {
         IDMethodContext var2 = ((IDInstruction)var1.get(0)).getContext();
         if (var1.allinsize() != 0) {
            return false;
         } else {
            BasicBlock var3 = null;
            int var4 = (int)((IDInstruction)var1.get(0)).getOffset();

            for (BasicBlock var6 : var0) {
               if (var6 != var1 && var6.size() >= 1 && ((IDInstruction)var6.getLast()).getOffsetEnd() == var4) {
                  var3 = var6;
                  break;
               }
            }

            if (var3 == null) {
               return false;
            } else {
               IDTryData var7 = var2.getExceptionData();
               var7.unprotectBlock((int)var1.getAddress());
               var0.removeBlock(var1);
               ((IDInstruction)var3.getLast()).adjustSize(((IDInstruction)var1.get(0)).getSize());
               return true;
            }
         }
      }
   }

   public static CFG copyGraph(CFG var0, boolean var1, IDMethodContext var2) {
      CFG var3 = new CFG(var0);
      if (var2 != null) {
         var3.setVariableInformationProvider(var2);
      }

      if (var1) {
         for (BasicBlock var5 : var3) {
            for (int var6 = 0; var6 < var5.size(); var6++) {
               IDInstruction var7 = ((IDInstruction)var5.get(var6)).duplicate();
               if (var2 != null) {
                  var7.setContext(var2);
               }

               var5.set(var6, var7);
            }
         }
      }

      return var3;
   }

   public static int normalizeGraph(IDMethodContext var0) {
      return normalizeGraph(var0, 1);
   }

   public static int normalizeGraph(IDMethodContext var0, int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException();
      } else {
         CFG var2 = var0.getCfg();
         int var3 = 0;
         HashMap var4 = new HashMap();
         int var5 = 0;

         for (IDInstruction var7 : var2.instructions()) {
            if (var7.getSize() != var1) {
               var3++;
            }

            var4.put((int)var7.getOffset(), var5);
            var5 += var1;
         }

         if (var3 == 0) {
            return 0;
         } else {
            var5 = 0;

            for (BasicBlock var13 : var2) {
               for (IDInstruction var9 : var13) {
                  var9.setSize(var1);
                  var9.setOffset(var5);
                  if (var9.getBreakingFlow().isBroken()) {
                     var9.updateTargets(var4);
                  }

                  var5 += var1;
               }
            }

            IDTryData var12 = var0.getExceptionData();
            var12.updateTargets(var4);
            var2.invalidateDataFlowAnalysis();
            return var3;
         }
      }
   }

   public static int modifyInstructionSizes(IDMethodContext var0, Function var1) {
      CFG var2 = var0.getCfg();
      int var3 = 0;
      ArrayList var4 = new ArrayList(var2.getInstructionCount());
      HashMap var5 = new HashMap();
      int var6 = 0;

      for (IDInstruction var8 : var2.instructions()) {
         Integer var9 = (Integer)var1.apply(var8);
         if (var9 == null) {
            var9 = var8.getSize();
         } else if (var9 != var8.getSize()) {
            var3++;
         }

         var4.add(var9);
         var5.put((int)var8.getOffset(), var6);
         var6 += var9;
      }

      if (var3 == 0) {
         return 0;
      } else {
         int var12 = 0;
         var6 = 0;

         for (IDInstruction var15 : var2.instructions()) {
            int var10 = (Integer)var4.get(var12);
            var15.setSize(var10);
            var15.setOffset(var6);
            if (var15.getBreakingFlow().isBroken()) {
               var15.updateTargets(var5);
            }

            var6 += var10;
            var12++;
         }

         IDTryData var14 = var0.getExceptionData();
         var14.updateTargets(var5);
         var2.invalidateDataFlowAnalysis();
         return var3;
      }
   }

   public static boolean modifyInstructionSize(IDMethodContext var0, IDInstruction var1, int var2) {
      if (var1.getSize() < var2) {
         modifyInstructionSizes(var0, var2x -> var2x == var1 ? var2 : null);
         return true;
      } else {
         return false;
      }
   }

   public static int updateTargets(IDMethodContext var0, Map var1) {
      int var2 = 0;

      for (IDInstruction var4 : var0.getCfg().instructions()) {
         if (var4.getBreakingFlow().isBroken()) {
            var2 += var4.updateTargets(var1);
         }
      }

      IDTryData var5 = var0.getExceptionData();
      return var2 + var5.updateTargets(var1);
   }

   public static void insertHeaderBlock(IDMethodContext var0, int var1, int var2) {
      if (var0 == null || var1 < 0 || var2 <= 0) {
         throw new IllegalArgumentException();
      } else if (var1 != 0) {
         int var3 = var1 * var2;
         int var4 = 1 + var3;
         CFG var5 = var0.getCfg();
         BasicBlock var6 = var5.get(0);
         IDInstruction var7 = (IDInstruction)var6.get(0);
         if (var7.getSize() < var4) {
            HashMap var8 = new HashMap();
            int var9 = 0;
            int var10 = 0;

            for (IDInstruction var12 : var5.instructions()) {
               var8.put((int)var12.getOffset(), var9);
               if (var10 == 0) {
                  var9 = var4;
               } else {
                  var9++;
               }

               var10++;
            }

            var9 = 0;
            var10 = 0;

            for (BasicBlock var24 : var5) {
               for (IDInstruction var14 : var24) {
                  if (var10 == 0) {
                     var14.setSize(var4);
                  } else {
                     var14.setSize(1);
                  }

                  var14.setOffset(var9);
                  if (var14.getBreakingFlow().isBroken()) {
                     var14.updateTargets(var8);
                  }

                  var9 += var14.getSize();
                  var10++;
               }
            }

            IDTryData var22 = var0.getExceptionData();
            var22.updateTargets(var8);
         }

         BasicBlock var15 = new BasicBlock();
         int var17 = 0;

         for (int var19 = 0; var19 < var1; var19++) {
            IDInstruction var23 = var0.createNop().withOffset(var17).withSize(var2);
            var15.add(var23);
            var17 += var2;
         }

         var5.addBlock(0, var15);
         var5.addEdge(var15, var6);
         var7.adjustSize(-var3);
         var7.setOffset(var3);
         HashMap var20 = new HashMap();
         var20.put(0, var3);
         updateTargets(var0, var20);
         var5.invalidateDataFlowAnalysis();
      }
   }

   public static BasicBlock splitBlock(IDMethodContext var0, BasicBlock var1, int var2) {
      if (var2 >= 0 && var2 <= var1.size()) {
         boolean var3 = false;
         IDTryData var4 = var0.getExceptionData();
         if (var4.isProtectedBlock((int)var1.getBase())) {
            var3 = true;
         }

         BasicBlock var5 = var0.getCfg().splitBlock(var1, var2);
         if (var3) {
            var4.copyProtectedBlock((int)var1.getBase(), (int)var5.getBase());
         }

         return var5;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static boolean mergeBlocks(IDMethodContext var0, BasicBlock var1) {
      BasicBlock var2 = var1;
      if (var1.outsize() == 1 && !((IDInstruction)var1.getLast()).getBreakingFlow().isBroken()) {
         BasicBlock var3 = var1.getOutputBlock(0);
         if (var3.irrinsize() == 0 && var3.insize() == 1 && var3.getBase() == var1.getEndAddress()) {
            int var4 = (int)var1.getBase();
            int var5 = (int)var3.getBase();
            IDTryData var6 = var0.getExceptionData();
            byte var7;
            if (var1.irroutsize() == 0) {
               if (var3.irroutsize() != 0 && var1.canThrow()) {
                  return false;
               }

               var7 = 1;
            } else if (var3.irroutsize() == 0) {
               if (var3.canThrow()) {
                  return false;
               }

               var7 = 2;
            } else {
               if (!var6.compareHandlers(var4, var5)) {
                  return false;
               }

               var7 = 3;
            }

            var1.addAll(var3.getInstructions());
            var3.removeAll();
            CFG var8 = var0.getCfg();
            if (var7 == 1) {
               for (BasicBlock var10 : var3.getIrregularOutputs()) {
                  var8.addIrregularEdge(var2, var10, -1);
               }

               var6.moveProtectedBlock(var5, var4);
            } else if (var7 != 2 && var7 == 3) {
               var8.deleteIrregularOutEdges(var3);
               var6.unprotectBlock(var5);
            }

            var8.removeBlock(var3);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static SortedMap generateBlockOffsetMap(IDMethodContext var0) {
      TreeMap var1 = new TreeMap();

      for (BasicBlock var3 : var0.getCfg()) {
         IDInstruction var4 = (IDInstruction)var3.get(0);
         int var5 = (int)var4.getOffset();
         int var6 = var4.getPhysicalOffset();
         var1.put(var5, var6);
      }

      return var1;
   }

   public static boolean isImmZero(IDExpression var0) {
      return isImmValue(var0, 0L);
   }

   public static boolean isImmValue(IDExpression var0, long var1) {
      try {
         return var0 instanceof IDImm && ((IDImm)var0).toLong(true) == var1;
      } catch (DexDecEvaluationException var3) {
         return false;
      }
   }

   public static boolean isImmNonZero(IDExpression var0) {
      try {
         return var0 instanceof IDImm && ((IDImm)var0).toLong(true) != 0L;
      } catch (DexDecEvaluationException var1) {
         return false;
      }
   }

   public static IDInstruction nextInstruction(CFG var0, BasicBlock var1, int var2) {
      var2++;

      while (true) {
         if (var2 == var1.size()) {
            if (var1.outsize() != 1) {
               return null;
            }

            var1 = var1.getOutputBlock(0);
            var2 = 0;
         }

         IDInstruction var3 = (IDInstruction)var1.get(var2);
         if (var3.isJump()) {
            var1 = var0.getBlockAt((long)var3.getBranchTarget());
            var2 = 0;
         } else {
            if (!var3.isNop()) {
               return var3;
            }

            var2++;
         }
      }
   }

   public static List unroll(IDMethodContext var0, IDVar var1, long var2, long var4, int var6, int var7, int var8) {
      CFG var9 = var0.getCfg();
      IDGlobalContext var10 = var0.getGlobalContext();
      HashMap var11 = new HashMap();
      boolean var12 = false;
      ArrayList var13 = new ArrayList();
      int var14 = 0;
      HashMap var15 = new HashMap();

      long var16;
      for (var16 = var2; var16 != var4 && var14 < var6 && var13.size() < var7; var14++) {
         IDInstruction var18 = (IDInstruction)var9.getInstruction(var16);
         if (var18 == null) {
            return null;
         }

         Integer var19 = (Integer)var11.compute(var16, (var0x, var1x) -> var1x == null ? 1 : var1x + 1);
         if (var19 > var8) {
            var12 = true;
         }

         Integer var20 = null;
         if (!var18.isNop()) {
            if (var18.isJump()) {
               var20 = var18.getBranchTarget();
            } else if (var18.isJcondOrSwitch()) {
               Set var21 = var18.getVarIds();
               if (var21.size() != 1 || (Integer)var21.iterator().next() != var1.getId()) {
                  return null;
               }

               try {
                  var20 = var18.evaluate(var15);
               } catch (DexDecEvaluationException var25) {
                  return null;
               }
            } else if (var18.isAssign()) {
               IDExpression var26 = var18.getAssignDestination();
               if (var26.equals(var1)) {
                  IDExpression var22 = var18.getAssignSource();
                  if (hasInvokeInfo(var22) || usesReferences(var22)) {
                     return null;
                  }

                  try {
                     IDImm var23 = var22.evaluate(var10, var15);
                     var15.put(var1.getId(), var23);
                  } catch (DexDecEvaluationException var24) {
                     return null;
                  }
               }

               if (var12 && var19 >= 2) {
                  return null;
               }

               var13.add(var18);
            } else {
               if (!var18.isInvoke()) {
                  return null;
               }

               if (var12 && var19 >= 2) {
                  return null;
               }

               var13.add(var18);
            }
         }

         if (var20 == null) {
            var20 = (int)var18.getOffsetEnd();
         }

         var16 = var20.intValue();
      }

      return var16 != var4 ? null : var13;
   }

   public static Collection determineInterval(BasicBlock var0) {
      LinkedHashSet var1 = new LinkedHashSet();
      var1.add(var0);

      while (true) {
         BasicBlock var2 = null;

         label33:
         for (BasicBlock var4 : var1) {
            for (BasicBlock var6 : var4.getAllOutputs()) {
               if (!var1.contains(var6) && var1.containsAll(var6.getInputs()) && var1.containsAll(var6.getIrregularInputs())) {
                  var2 = var6;
                  break label33;
               }
            }
         }

         if (var2 == null) {
            return var1;
         }

         var1.add(var2);
      }
   }

   public static boolean canThrow(BasicBlock var0, int var1) {
      return canThrow(var0, var1, var0.size());
   }

   public static boolean canThrow(BasicBlock var0, int var1, int var2) {
      for (int var3 = var1; var3 < var2; var3++) {
         IDInstruction var4 = (IDInstruction)var0.get(var3);
         if (var4.canThrow()) {
            return true;
         }
      }

      return false;
   }

   public static boolean isProtectedByCatchAll(IDMethodContext var0, BasicBlock var1) {
      if (var1.irroutsize() == 0) {
         return false;
      } else {
         for (IDExceptionHandler var4 : var0.getExceptionData().getBlockHandlers((int)var1.getBase())) {
            if (var4.isCatchAll(var0)) {
               return true;
            }
         }

         return false;
      }
   }

   private static class CATDeterminer implements IDVisitor {
      IDexContextInfoProvider ciprv;
      ContextAccessType cat = ContextAccessType.NONE;

      CATDeterminer(IDexContextInfoProvider var1) {
         this.ciprv = var1;
      }

      public void process(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDCallInfo) {
            ContextAccessType var4 = this.ciprv.getMethodCAT(((IDCallInfo)var1).getMethodSignature());
            this.cat = this.cat.addAccess(var4);
            if (this.cat.isAllAccess()) {
               var3.interrupt(true);
            }
         } else if (var1 instanceof IDArrayElt || var1 instanceof IDInstanceField || var1 instanceof IDStaticField) {
            this.cat = this.cat.addAccess(ContextAccessType.READ_ONLY);
            if (this.cat.isAllAccess()) {
               var3.interrupt(true);
            }
         }
      }
   }
}
