package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CFGUtil;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStorageEntryGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jebglobal.all;
import com.pnfsoftware.jebglobal.alp;
import com.pnfsoftware.jebglobal.alu;
import com.pnfsoftware.jebglobal.amf;
import com.pnfsoftware.jebglobal.aml;
import com.pnfsoftware.jebglobal.anc;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EUtil {
   private static final BigInteger MINUS_ONE = BigInteger.valueOf(-1L);

   public static String formatStatements(List var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (IEStatement var4 : var0) {
         Strings.ff(var1, "%04X : %s  [LLA=%s]\n", var2, var4.format(null), Longs.formatHexCollection(var4.getLowerLevelAddresses()));
         var2++;
      }

      return var1.toString();
   }

   public static String formatIR(IERoutineContext var0) {
      return formatIR(var0, true, true, true);
   }

   public static String formatIR(IERoutineContext var0, boolean var1, boolean var2, boolean var3) {
      CFG var4 = var0.getCfg();
      aml var5 = (aml)var0;
      ECFGFormatter var6 = new ECFGFormatter(var4, new anc(var0), var5.nf());
      var6.setContext(var0);
      IDFA var7 = null;
      if (var2) {
         var7 = var4.doDataFlowAnalysis();
      }

      EUtil$1 var8 = new EUtil$1(var3);
      return var6.format(var1, var2, var2, var7, var8);
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

         if (var2 == null) {
            var2 = var1;
         }

         CFGUtil.toDot(var0, IO.createTempFile(IO.sanitizePathUnsafe(var1)), var2, null, -1);
      } catch (IOException var3) {
      }
   }

   public static void verify(IERoutineContext var0) {
      new all(var0).verify();
   }

   public static void verify(CFG var0) {
      new all(var0).verify();
   }

   public static boolean isPCAssign(IEStatement var0) {
      if (var0 instanceof IEAssign) {
         IEAssign var1 = var0.asAssign();
         if (var1.getDstOperand().isVar()) {
            IEVar var2 = var0.getContext().getProgramCounter();
            IEVar var3 = var1.getDstOperand().asVar();
            return var3 == var2;
         }
      }

      return false;
   }

   public static boolean isSPAssign(IEStatement var0) {
      if (var0 instanceof IEAssign) {
         IEAssign var1 = var0.asAssign();
         if (var1.getDstOperand().isVar()) {
            IEVar var4 = var0.getContext().getStackPointer();
            IEVar var5 = var1.getDstOperand().asVar();
            return var5 == var4;
         }

         if (var1.getDstOperand().isSlice()) {
            IEVar var2 = var0.getContext().getStackPointer();
            IESlice var3 = var1.getDstOperand().asSlice();
            return var3.getWholeExpression() == var2;
         }
      }

      return false;
   }

   public static boolean isSPAssignOrPCAssign(IEStatement var0) {
      if (var0 instanceof IEAssign) {
         IEAssign var1 = var0.asAssign();
         if (var1.getDstOperand().isVar()) {
            IEVar var5 = var0.getContext().getStackPointer();
            IEVar var6 = var0.getContext().getProgramCounter();
            IEVar var4 = var1.getDstOperand().asVar();
            return var4 == var5 || var4 == var6;
         }

         if (var1.getDstOperand().isSlice()) {
            IEVar var2 = var0.getContext().getStackPointer();
            IESlice var3 = var1.getDstOperand().asSlice();
            return var3.getWholeExpression() == var2;
         }
      }

      return false;
   }

   public static IEImm imm(long var0, int var2) {
      return alu.q(var0, var2);
   }

   public static IEImm imm(byte[] var0, int var1) {
      return alu.q(var0, var1);
   }

   public static IEImm imm(BigInteger var0, int var1) {
      return alu.q(var0, var1);
   }

   public static IEImm imm(String var0, int var1) {
      return alu.q(var0, var1);
   }

   public static IEImm one(int var0) {
      return imm(1L, var0);
   }

   public static IEImm zero(int var0) {
      return imm(0L, var0);
   }

   public static IEImm minusOne(int var0) {
      return imm(-1L, var0);
   }

   public static IEImm mask(int var0, int var1) {
      Assert.a(var1 <= var0);
      return imm(-1L, var0)._shr(var0 - var1);
   }

   public static IEImm mask(int var0, int var1, int var2) {
      int var3 = var2 - var1;
      return imm(-1L, var0)._shr(var0 - var3)._shl(var1);
   }

   public static IEGeneric compose(IERoutineContext var0, IEGeneric... var1) {
      return (IEGeneric)(var1.length == 1 ? var1[0] : var0.createCompose(var1));
   }

   public static IEGeneric compose(IERoutineContext var0, Collection var1) {
      return (IEGeneric)(var1.size() == 1 ? (IEGeneric)var1.iterator().next() : var0.createCompose(var1));
   }

   public static IEGeneric checkTrue(IEGeneric var0) {
      return (IEGeneric)(var0.getBitsize() == 1 ? var0 : op(OperationType.LOG_NEQ, var0, zero(var0.getBitsize())));
   }

   public static IEGeneric checkFalse(IEGeneric var0) {
      return var0.getBitsize() == 1 ? op(OperationType.LOG_NOT, var0) : op(OperationType.LOG_EQ, var0, zero(var0.getBitsize()));
   }

   public static boolean isOperation(IEGeneric var0, OperationType var1) {
      return var0 instanceof IEOperation && var0.asOperation().getOperationType() == var1;
   }

   public static boolean isOperation(IEGeneric var0, OperationType... var1) {
      return getOperation(var0, var1) != null;
   }

   public static boolean isLogicalOperation(IEGeneric var0) {
      return !(var0 instanceof IEOperation) ? false : isStrictLogicalOperation(var0.asOperation()) || isComparableOperation(var0.asOperation());
   }

   public static boolean isComparableOperation(IEGeneric var0) {
      return !(var0 instanceof IEOperation) ? false : isComparableOperation(var0.asOperation());
   }

   public static boolean isComparableOperation(IEOperation var0) {
      return isComparableOperation(var0, true, true);
   }

   public static boolean isComparableIntegerOperation(IEOperation var0) {
      return isComparableOperation(var0, true, false);
   }

   public static boolean isComparableSignedOperation(IEOperation var0) {
      return isComparableOperation(var0, false, false);
   }

   public static boolean isComparableOperation(IEOperation var0, boolean var1, boolean var2) {
      OperationType var3 = var0.getOperationType();
      switch (var3) {
         case LOG_EQ:
         case LOG_NEQ:
         case GE_S:
         case GT_S:
         case LE_S:
         case LT_S:
            return true;
         case GE_U:
         case GT_U:
         case LE_U:
         case LT_U:
            return var1;
         case FEQ:
         case FNE:
         case FGE:
         case FGT:
         case FLE:
         case FLT:
            return var2;
         default:
            return false;
      }
   }

   public static boolean isStrictLogicalOperation(IEGeneric var0) {
      return !(var0 instanceof IEOperation) ? false : isStrictLogicalOperation(var0.asOperation());
   }

   public static boolean isStrictLogicalOperation(IEOperation var0) {
      OperationType var1 = var0.getOperationType();
      return var1.isAnyOf(OperationType.LOG_AND, OperationType.LOG_NOT, OperationType.LOG_OR);
   }

   public static OperationType getOperation(IEGeneric var0, OperationType... var1) {
      return var0 instanceof IEOperation ? getOperation(var0.asOperation(), var1) : null;
   }

   public static OperationType getOperation(IEOperation var0, OperationType... var1) {
      OperationType var2 = var0.getOperationType();

      for (OperationType var6 : var1) {
         if (var6 == var2) {
            return var2;
         }
      }

      return null;
   }

   public static boolean isOperationSize(IEGeneric var0, int var1) {
      return var0 instanceof IEOperation && var0.asOperation().getBitsize() == var1;
   }

   public static OperationType getReverseOperation(OperationType var0) {
      if (var0 == null) {
         return null;
      } else {
         switch (var0) {
            case LOG_EQ:
               return OperationType.LOG_NEQ;
            case LOG_NEQ:
               return OperationType.LOG_EQ;
            case GE_S:
               return OperationType.LT_S;
            case GT_S:
               return OperationType.LE_S;
            case LE_S:
               return OperationType.GT_S;
            case LT_S:
               return OperationType.GE_S;
            case GE_U:
               return OperationType.LT_U;
            case GT_U:
               return OperationType.LE_U;
            case LE_U:
               return OperationType.GT_U;
            case LT_U:
               return OperationType.GE_U;
            case FEQ:
               return OperationType.FNE;
            case FNE:
               return OperationType.FEQ;
            case FGE:
               return OperationType.FLT;
            case FGT:
               return OperationType.FLE;
            case FLE:
               return OperationType.FGT;
            case FLT:
               return OperationType.FGE;
            default:
               return null;
         }
      }
   }

   public static OperationType getMirrorOperation(OperationType var0) {
      if (var0 == null) {
         return null;
      } else {
         switch (var0) {
            case LOG_EQ:
               return OperationType.LOG_EQ;
            case LOG_NEQ:
               return OperationType.LOG_NEQ;
            case GE_S:
               return OperationType.LE_S;
            case GT_S:
               return OperationType.LT_S;
            case LE_S:
               return OperationType.GE_S;
            case LT_S:
               return OperationType.GT_S;
            case GE_U:
               return OperationType.LE_U;
            case GT_U:
               return OperationType.LT_U;
            case LE_U:
               return OperationType.GE_U;
            case LT_U:
               return OperationType.GT_U;
            case FEQ:
               return OperationType.FEQ;
            case FNE:
               return OperationType.FNE;
            case FGE:
               return OperationType.FLE;
            case FGT:
               return OperationType.FLT;
            case FLE:
               return OperationType.FGE;
            case FLT:
               return OperationType.FGT;
            default:
               return null;
         }
      }
   }

   public static boolean isVar(IEGeneric var0, int var1) {
      return var0 instanceof IEVar && var0.asVar().getId() == var1;
   }

   public static boolean isVarOrVarSlice(IEGeneric var0) {
      return var0 instanceof IEVar || var0 instanceof IESlice && var0.asSlice().getWholeExpression().isVar();
   }

   public static IEVar getVarSlice(IEGeneric var0) {
      if (var0 instanceof IEVar) {
         return var0.asVar();
      } else {
         return var0 instanceof IESlice && var0.asSlice().getWholeExpression().isVar() ? var0.asSlice().getWholeExpression().asVar() : null;
      }
   }

   public static Integer getVarSliceId(IEGeneric var0) {
      IEVar var1 = getVarSlice(var0);
      return var1 == null ? null : var1.getId();
   }

   public static boolean isZero(IEGeneric var0) {
      return isImmZero(var0) || var0 instanceof IESlice && isImmZero(var0.asSlice().getWholeExpression());
   }

   public static boolean isOne(IEGeneric var0) {
      return isImmValue(var0, BigInteger.ONE);
   }

   public static boolean isMinusOne(IEGeneric var0) {
      return isImmValue(var0, MINUS_ONE);
   }

   public static boolean isBitOne(IEGeneric var0) {
      return var0.getBitsize() == 1 && var0 instanceof IEImm && var0.asImm().getValue().longValue() == -1L;
   }

   public static boolean isBitZero(IEGeneric var0) {
      return var0.getBitsize() == 1 && var0 instanceof IEImm && var0.asImm().getValue().longValue() == 0L;
   }

   public static boolean isImmSize(IEGeneric var0, int var1) {
      return var0 instanceof IEImm && var0.asImm().getBitsize() == var1;
   }

   public static boolean isImmValue(IEGeneric var0, long var1) {
      return isImmValue(var0, BigInteger.valueOf(var1));
   }

   public static boolean isImmValue(IEGeneric var0, BigInteger var1) {
      return var0 instanceof IEImm && var0.asImm().getValue().equals(var1);
   }

   public static boolean isImmZero(IEGeneric var0) {
      return var0 instanceof IEImm && var0.asImm()._signum() == 0;
   }

   public static boolean isImmNonZero(IEGeneric var0) {
      return var0 instanceof IEImm && var0.asImm()._signum() != 0;
   }

   public static boolean isCondEAssign(IEStatement var0) {
      return var0 instanceof IEAssign && var0.asAssign().getSrcOperand() instanceof IECond;
   }

   public static boolean isJump(IEGeneric var0) {
      return var0 instanceof IEJump;
   }

   public static boolean isUnconditionalJump(IEStatement var0) {
      return var0 instanceof IEJump && var0.asJump().getCondition() == null;
   }

   public static boolean isConditionalJump(IEStatement var0) {
      return var0 instanceof IEJump && var0.asJump().getCondition() != null;
   }

   public static void makeUncond(IEJumpWithOptionalCondition var0) {
      if (containsUndeterminedInvocations(var0.getCondition())) {
         throw new RuntimeException("Tried to remove an EJump condition containing undetermined calls");
      } else {
         var0.setCondition(null);
      }
   }

   public static boolean isAssignedIn(BasicBlock var0, IEGeneric var1) {
      boolean var2 = false;

      for (IEStatement var4 : var0) {
         if (var4.isAssign() && var1.equals(var4.asAssign().getLeftOperand())) {
            var2 = true;
            break;
         }
      }

      return var2;
   }

   public static List getSubExpressions(IEGeneric var0) {
      ArrayList var1 = new ArrayList();
      var0.collectSubExpressions(var1);
      return var1;
   }

   public static int countSubExpressions(IEGeneric var0) {
      return getSubExpressions(var0).size();
   }

   public static int sameBitsize(IEGeneric... var0) {
      int var1 = 0;

      for (IEGeneric var5 : var0) {
         if (var5 == null) {
            return 0;
         }

         if (var1 == 0) {
            var1 = var5.getBitsize();
         } else if (var1 != var5.getBitsize()) {
            return 0;
         }
      }

      return var1;
   }

   public static int sameBitsizeAllowNulls(IEGeneric... var0) {
      int var1 = 0;

      for (IEGeneric var5 : var0) {
         if (var5 != null) {
            if (var1 == 0) {
               var1 = var5.getBitsize();
            } else if (var1 != var5.getBitsize()) {
               return 0;
            }
         }
      }

      return var1;
   }

   public static int countVariableUse(IEGeneric var0) {
      return var0.getUsed().size();
   }

   public static int calculateComplexity(IEGeneric var0) {
      int var1 = 1;
      ArrayList var2 = new ArrayList();
      var0.collectSubExpressions(var2);

      for (IEGeneric var4 : var2) {
         var1 += calculateComplexity(var4);
      }

      return var1;
   }

   public static int countVariablePresence(IEGeneric var0, IEVar var1) {
      if (var0 == var1) {
         return 1;
      } else {
         ArrayList var2 = new ArrayList();
         var0.collectSubExpressions(var2);
         int var3 = 0;

         for (IEGeneric var5 : var2) {
            var3 += countVariablePresence(var5, var1);
         }

         return var3;
      }
   }

   public static int countExpressionPresence(IEGeneric var0, IEGeneric var1) {
      int var2 = 0;
      if (var1.equals(var0)) {
         var2++;
      }

      ArrayList var3 = new ArrayList();
      var0.collectSubExpressions(var3);

      for (IEGeneric var5 : var3) {
         var2 += countExpressionPresence(var5, var1);
      }

      return var2;
   }

   public static void countExpressionsPresence(IEGeneric var0, List var1, int[] var2) {
      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (((IEGeneric)var1.get(var3)).equals(var0)) {
            var2[var3]++;
         }
      }

      ArrayList var6 = new ArrayList();
      var0.collectSubExpressions(var6);

      for (IEGeneric var5 : var6) {
         countExpressionsPresence(var5, var1, var2);
      }
   }

   public static Set getUsedVarIds(IEGeneric var0) {
      HashSet var1 = new HashSet();
      var0.getUsed().collectVarIds(var1);
      return var1;
   }

   public static Set collectVars(IEGeneric var0) {
      HashSet var1 = new HashSet();
      collectVars(var0, var1);
      return var1;
   }

   public static void collectVars(IEGeneric var0, Collection var1) {
      if (var0 instanceof IEVar) {
         var1.add(var0.asVar());
      } else {
         ArrayList var2 = new ArrayList();
         var0.collectSubExpressions(var2);

         for (IEGeneric var4 : var2) {
            collectVars(var4, var1);
         }
      }
   }

   public static boolean hasExplicitlyUsedVar(IEGeneric var0, IEVar var1) {
      EDefUseInfo var2 = new EDefUseInfo();
      var0.getExplicitlyUsed(var2);
      return var2.getUse().containsVarPart(var1);
   }

   public static boolean isTrampoline(BasicBlock var0) {
      if (var0.outsize() != 1) {
         return false;
      } else {
         for (int var1 = 0; var1 < var0.size() - 1; var1++) {
            if (!(var0.get(var1) instanceof IENop)) {
               return false;
            }
         }

         AddressableInstruction var2 = var0.getLast2();
         return var2.getBreakingFlow().isBroken() || ((IEStatement)var2.getInstruction()).isNop();
      }
   }

   public static IEGeneric reversePredicate(IEGeneric var0) {
      return op(OperationType.LOG_NOT, var0);
   }

   public static boolean containsUndeterminedInvocations(IEGeneric var0) {
      return !var0.visitDepthPre(new EUtil$2());
   }

   public static boolean containsMemoryAccess(IEGeneric var0) {
      return !var0.visitDepthPre(new EUtil$3());
   }

   public static boolean contains(IEGeneric var0, IEGeneric var1) {
      return !var0.visitDepthPre(new EUtil$4(var1));
   }

   public static IEOperation buildStrictLogicalOperation(IEOperation var0) {
      return !isStrictLogicalOperation(var0) ? var0 : buildLogicalOperation(var0);
   }

   public static IEOperation buildLogicalOperation(IEOperation var0) {
      boolean var1 = false;
      Object var2 = var0.getOperand1();
      Object var3 = var0.getOperand2();
      if (!isLogicalOperation((IEGeneric)var2)) {
         var2 = ne((IEGeneric)var2, zero(((IEGeneric)var2).getBitsize()));
         var1 = true;
      }

      if (var3 != null && !isLogicalOperation((IEGeneric)var3)) {
         var3 = ne((IEGeneric)var3, zero(((IEGeneric)var3).getBitsize()));
         var1 = true;
      }

      return var1 ? op(var0.getOperationType(), (IEGeneric)var2, (IEGeneric)var3) : var0;
   }

   public static IECond buildStrictLogicalECond(IECond var0) {
      IEGeneric var1 = var0.getCondition();
      return (IECond)(!isLogicalOperation(var1) ? new alp(ne(var1, zero(var1.getBitsize())), var0.getExpressionTrue(), var0.getExpressionFalse()) : var0);
   }

   public static IEOperation op(OperationType var0, IEGeneric var1) {
      return new amf(var0, var1);
   }

   public static IEOperation op(OperationType var0, IEGeneric var1, IEGeneric var2) {
      return new amf(var0, var1, var2);
   }

   public static IEOperation ltS(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LT_S, var0, var1);
   }

   public static IEOperation leS(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LE_S, var0, var1);
   }

   public static IEOperation gtS(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.GT_S, var0, var1);
   }

   public static IEOperation geS(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.GE_S, var0, var1);
   }

   public static IEOperation ltU(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LT_U, var0, var1);
   }

   public static IEOperation leU(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LE_U, var0, var1);
   }

   public static IEOperation gtU(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.GT_U, var0, var1);
   }

   public static IEOperation geU(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.GE_U, var0, var1);
   }

   public static IEOperation eq(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LOG_EQ, var0, var1);
   }

   public static IEOperation ne(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LOG_NEQ, var0, var1);
   }

   public static IEOperation andL(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LOG_AND, var0, var1);
   }

   public static IEOperation orL(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.LOG_OR, var0, var1);
   }

   public static IEGeneric notL(IEGeneric var0) {
      return notL(var0, true);
   }

   public static IEGeneric notL(IEGeneric var0, boolean var1) {
      if (var1 && var0 instanceof IEOperation) {
         OperationType var2 = var0.asOperation().getOperationType();
         if (var2 == OperationType.LOG_NOT) {
            return var0.asOperation().getOperand1();
         }

         OperationType var3 = getReverseOperation(var2);
         if (var3 != null) {
            return op(var3, var0.asOperation().getOperand1(), var0.asOperation().getOperand2());
         }
      }

      return op(OperationType.LOG_NOT, var0);
   }

   public static IEGeneric identL(IEGeneric var0) {
      if (isImmZero(var0)) {
         return zero(1);
      } else if (isImmNonZero(var0)) {
         return one(1);
      } else if (var0 instanceof IEOperation && var0.asOperation().getOperationType() == OperationType.LOG_NOT) {
         IEGeneric var1 = var0.asOperation().getOperand1();
         return op(OperationType.LOG_EQ, var1, zero(var1.getBitsize()));
      } else {
         return op(OperationType.LOG_NEQ, var0, zero(var0.getBitsize()));
      }
   }

   public static IEOperation andB(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.AND, var0, var1);
   }

   public static IEOperation orB(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.OR, var0, var1);
   }

   public static IEOperation xorB(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.XOR, var0, var1);
   }

   public static IEGeneric notB(IEGeneric var0) {
      return (IEGeneric)(isOperation(var0, OperationType.NOT) ? var0.asOperation().getOperand1() : op(OperationType.NOT, var0));
   }

   public static IEOperation add(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.ADD, var0, var1);
   }

   public static IEOperation add(IEGeneric var0, IEGeneric var1, IEGeneric var2) {
      return add(add(var0, var1), var2);
   }

   public static IEOperation sub(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.SUB, var0, var1);
   }

   public static IEOperation mul(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.MUL, var0, var1);
   }

   public static IEOperation div(IEGeneric var0, IEGeneric var1, boolean var2) {
      return var2 ? divS(var0, var1) : divU(var0, var1);
   }

   public static IEOperation divS(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.DIV_S, var0, var1);
   }

   public static IEOperation divU(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.DIV_U, var0, var1);
   }

   public static IEOperation remS(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.REM_S, var0, var1);
   }

   public static IEOperation remU(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.REM_U, var0, var1);
   }

   public static IEOperation shl(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.SHL, var0, var1);
   }

   public static IEOperation shr(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.SHR, var0, var1);
   }

   public static IEOperation sar(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.SAR, var0, var1);
   }

   public static IEOperation pow(IEGeneric var0, IEGeneric var1) {
      return op(OperationType.POW, var0, var1);
   }

   public static IECond min(IERoutineContext var0, IEGeneric var1, IEGeneric var2, boolean var3) {
      return var0.createCond(var0.createOperation(var3 ? OperationType.GT_S : OperationType.GT_U, var1, var2), var2, var1);
   }

   public static IECond max(IERoutineContext var0, IEGeneric var1, IEGeneric var2, boolean var3) {
      return var0.createCond(var0.createOperation(var3 ? OperationType.GT_S : OperationType.GT_U, var1, var2), var1, var2);
   }

   public static IECond abs(IERoutineContext var0, IEGeneric var1) {
      IEImm var2 = var0.createImm(0L, var1.getBitsize());
      return var0.createCond(var0.createOperation(OperationType.LT_S, var1, var2), sub(var2, var1), var1);
   }

   public static IECond fmin(IERoutineContext var0, IEGeneric var1, IEGeneric var2) {
      return var0.createCond(var0.createOperation(OperationType.FGT, var1, var2), var2, var1);
   }

   public static IECond fmax(IERoutineContext var0, IEGeneric var1, IEGeneric var2) {
      return var0.createCond(var0.createOperation(OperationType.FGT, var1, var2), var1, var2);
   }

   public static IECond fabs(IERoutineContext var0, IEGeneric var1) {
      IEImm var2 = var0.createImm(0L, var1.getBitsize());
      return var0.createCond(var0.createOperation(OperationType.FLT, var1, var2), op(OperationType.FSUB, var2, var1), var1);
   }

   public static IEGeneric extend(IEGeneric var0, int var1, boolean var2) {
      if (var0 == null) {
         return null;
      } else {
         return var2 ? var0.signExtend(var1) : var0.zeroExtend(var1);
      }
   }

   public static IEGeneric safeExtend(IEGeneric var0, int var1, boolean var2) {
      if (var0 == null) {
         return null;
      } else if (var1 == var0.getBitsize()) {
         return var0;
      } else if (var1 < var0.getBitsize()) {
         return var0.part(var1);
      } else {
         return var2 ? var0.signExtend(var1) : var0.zeroExtend(var1);
      }
   }

   public static boolean isNotPredicate(IEGeneric var0, IEGeneric var1) {
      if (var0 instanceof IEOperation && var1 instanceof IEOperation) {
         IEOperation var2 = var0.asOperation();
         IEOperation var3 = var1.asOperation();
         OperationType var4 = var2.getOperationType();
         OperationType var5 = getReverseOperation(var4);
         if (var5 == null) {
            return false;
         }

         if (var5 == var3.getOperationType() && var2.getOperand1().equals(var3.getOperand1()) && var2.getOperand2().equals(var3.getOperand2())) {
            return true;
         }

         var5 = getMirrorOperation(var5);
         if (var5 != null && var5 == var3.getOperationType() && var2.getOperand1().equals(var3.getOperand2()) && var2.getOperand2().equals(var3.getOperand1())) {
            return true;
         }
      }

      return false;
   }

   public static boolean isFirstBit(IESlice var0) {
      return var0.getBitsize() == 1 && var0.getBitStart() == 0;
   }

   public static boolean isLastBit(IESlice var0) {
      return var0.getBitsize() == 1 && var0.getBitEnd() == var0.getWholeExpression().getBitsize();
   }

   public static boolean isNBit(IESlice var0, int var1) {
      return var0.getBitsize() == 1 && var0.getBitStart() == var1;
   }

   private static int getFloatingExponentSize(int var0) {
      switch (var0) {
         case 16:
            return 5;
         case 32:
            return 8;
         case 64:
            return 11;
         case 128:
            return 15;
         case 256:
            return 19;
         default:
            throw new RuntimeException("Illegal Bitsize for float: " + var0);
      }
   }

   public static IEGeneric isNaN(IEGeneric var0) {
      int var1 = getFloatingExponentSize(var0.getBitsize());
      int var2 = var0.getBitsize() - var1 - 1;
      return andL(eq(var0.slice(var2, var2 + var1), imm(MathUtil.makeMask(var1), var1)), ne(var0.part(var2), imm(0L, var2)));
   }

   public static IEGeneric cmpFloat(OperationType var0, IEGeneric var1, IEGeneric var2) {
      return andL(andL(notL(isNaN(var1)), notL(isNaN(var2))), op(var0, var1, var2));
   }

   public static IEOperation createResizeOperation(IEGeneric var0, int var1, boolean var2) {
      return amf.q(var0, var1, var2);
   }

   public static IEOperation createConversionOperation(OperationType var0, IEGeneric var1, int var2) {
      return amf.q(var0, var1, var2);
   }

   public static IEGeneric truncate(IEGeneric var0, int var1) {
      if (var1 > var0.getBitsize()) {
         throw new IllegalArgumentException();
      } else {
         return (IEGeneric)(var1 == var0.getBitsize() ? var0 : createResizeOperation(var0, var1, false));
      }
   }

   public static IEGeneric zeroExt(IEGeneric var0, int var1) {
      if (var1 < var0.getBitsize()) {
         throw new IllegalArgumentException();
      } else {
         return (IEGeneric)(var1 == var0.getBitsize() ? var0 : createResizeOperation(var0, var1, false));
      }
   }

   public static IEGeneric signExt(IEGeneric var0, int var1) {
      if (var1 < var0.getBitsize()) {
         throw new IllegalArgumentException();
      } else {
         return (IEGeneric)(var1 == var0.getBitsize() ? var0 : createResizeOperation(var0, var1, true));
      }
   }

   public static boolean looksLikeSignExtension(IECompose var0) {
      return getSignExtensionBase(var0) != null;
   }

   public static IEGeneric getSignExtensionBase(IEGeneric var0) {
      return var0 instanceof IECompose ? getSignExtensionBase(var0.asCompose()) : null;
   }

   public static IEGeneric getSignExtensionBase(IECompose var0) {
      return var0.getPartsCount() == 2 ? getSignExtensionBase(var0.getLowPart(), var0.getHighPart()) : null;
   }

   public static IEGeneric getSignExtensionBase(IEGeneric var0, IEGeneric var1) {
      if (var1 instanceof IECond) {
         IEGeneric var2 = var1.asCond().getCondition();
         IEGeneric var3 = var1.asCond().getExpressionTrue();
         IEGeneric var4 = var1.asCond().getExpressionFalse();
         if (isImmValue(var3, -1L)
            && isImmZero(var4)
            && var2 instanceof IESlice
            && isNBit((IESlice)var2, var0.getBitsize() - 1)
            && isWholeExpressionOf((IESlice)var2, var0)) {
            return var0;
         }
      }

      return null;
   }

   private static boolean isWholeExpressionOf(IESlice var0, IEGeneric var1) {
      if (var0.getWholeExpression().equals(var1)) {
         return true;
      } else if (!(var1 instanceof IESlice)) {
         return false;
      } else {
         IESlice var2 = var1.asSlice();
         return var2.getBitStart() == 0 && var0.getWholeExpression().equals(var2.getWholeExpression());
      }
   }

   public static boolean isZeroExtend(IEGeneric var0) {
      return var0 instanceof IECompose && isZeroExtend(var0.asCompose());
   }

   public static boolean isZeroExtend(IECompose var0) {
      return var0.getPartsCount() == 2 && isZero(var0.getHighPart());
   }

   public static IEGeneric buildCarryFlag(IEGeneric var0, IEGeneric var1, IEGeneric var2, boolean var3, boolean var4) {
      if (var4) {
         return var3 ? op(OperationType.CARRY, var0, var1) : ltU(var0, var1);
      } else {
         IEOperation var5 = xorB(xorB(var0.duplicate(), var1.duplicate()), var2.duplicate());
         IEOperation var6 = xorB(var0.duplicate(), var2.duplicate());
         Object var7 = var3 ? notB(xorB(var0.duplicate(), var1.duplicate())) : xorB(var0.duplicate(), var1.duplicate());
         IEOperation var8 = xorB(var5, andB(var6, (IEGeneric)var7));
         return var8.msb();
      }
   }

   public static IEGeneric buildOverflowFlag(IEGeneric var0, IEGeneric var1, IEGeneric var2, boolean var3) {
      IEOperation var4 = xorB(var0.duplicate(), var2.duplicate());
      Object var5 = var3 ? notB(xorB(var0.duplicate(), var1.duplicate())) : xorB(var0.duplicate(), var1.duplicate());
      IEOperation var6 = andB(var4, (IEGeneric)var5);
      return var6.msb();
   }

   public static boolean isLongImmediate(IEGeneric var0) {
      return isLongImmediate(var0, false);
   }

   public static boolean isLongImmediate(IEGeneric var0, boolean var1) {
      if (!(var0 instanceof IEImm)) {
         return false;
      } else {
         return var1 ? var0.asImm().canReadAsUnsignedLong() : var0.asImm().canReadAsLong();
      }
   }

   public static boolean isLikeLongImmediate(IEGeneric var0) {
      return isLikeLongImmediate(var0, false);
   }

   public static boolean isLikeLongImmediate(IEGeneric var0, boolean var1) {
      return isLongImmediate(var0, var1) || isEComposeLongImm(var0, var1) || isESliceLongImm(var0, var1);
   }

   private static boolean isESliceLongImm(IEGeneric var0, boolean var1) {
      return var0 instanceof IESlice && isLikeLongImmediate(var0.asSlice().getWholeExpression(), var1);
   }

   private static boolean isEComposeLongImm(IEGeneric var0, boolean var1) {
      if (!(var0 instanceof IECompose)) {
         return false;
      } else {
         for (IEGeneric var3 : var0.asCompose()) {
            if (!isLikeLongImmediate(var3, var1)) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean isImmediate(IEGeneric var0) {
      return var0 instanceof IEImm;
   }

   public static boolean isLikeImmediate(IEGeneric var0) {
      return isImmediate(var0) || isEComposeImm(var0) || isESliceImm(var0);
   }

   private static boolean isESliceImm(IEGeneric var0) {
      return var0 instanceof IESlice && isLikeImmediate(var0.asSlice().getWholeExpression());
   }

   private static boolean isEComposeImm(IEGeneric var0) {
      if (!(var0 instanceof IECompose)) {
         return false;
      } else {
         for (IEGeneric var2 : var0.asCompose()) {
            if (!isLikeImmediate(var2)) {
               return false;
            }
         }

         return true;
      }
   }

   public static List getParents(IEGeneric var0, IEGeneric var1) {
      ArrayList var2 = new ArrayList();
      var0.visitDepthPre(new EUtil$5(var1, var2));
      return var2;
   }

   public static int evalAsSaturatedPositiveInt(IEImm var0) {
      long var1 = var0.getValueAsUnsignedLong();
      return var1 <= 2147483647L ? (int)var1 : Integer.MAX_VALUE;
   }

   public static int evalAsPositiveInt(IEImm var0) {
      return (int)(var0.getValueAsLong() & 2147483647L);
   }

   public static IEImm evaluate_preVerified(IEGeneric var0, EState var1) {
      try {
         return var0.evaluate(var1);
      } catch (Exception var3) {
         throw new EvaluationException("Unexpected evaluate() error", var3);
      }
   }

   public static IEImm evaluate_preVerified(IEGeneric var0) {
      return evaluate_preVerified(var0, null);
   }

   public static long evaluateUnsignedLong_preVerified(IEGeneric var0, EState var1) {
      try {
         return var0.evaluate(var1).getValueAsUnsignedLong();
      } catch (Exception var3) {
         throw new EvaluationException("Unexpected evaluate() error", var3);
      }
   }

   public static long evaluateUnsignedLong_preVerified(IEGeneric var0) {
      return evaluateUnsignedLong_preVerified(var0, null);
   }

   public static long evaluateAddress_preVerified(IEGeneric var0, EState var1) {
      try {
         return var0.evaluate(var1).getValueAsAddress();
      } catch (Exception var3) {
         throw new EvaluationException("Unexpected evaluate() error", var3);
      }
   }

   public static long evaluateAddress_preVerified(IEGeneric var0) {
      return evaluateAddress_preVerified(var0, null);
   }

   public static IEGeneric replaceSubExpressionRecursive(IEGeneric var0, IEGeneric var1, IEGeneric var2) {
      return replaceSubExpressionRecursive(var0, var1, var2, null);
   }

   public static IEGeneric replaceSubExpressionRecursive(IEGeneric var0, IEGeneric var1, IEGeneric var2, EVisitResults var3) {
      if (var0.equals(var1)) {
         return var2;
      } else {
         if (var3 == null) {
            var3 = new EVisitResults();
         }

         var0.visitDepthPost(new EUtil$6(var1, var2), null, var3);
         return var0;
      }
   }

   public static boolean resolveExpressionsBackward(Object var0, IEConverter var1, List var2, IEGeneric... var3) {
      if (var2.size() == 0) {
         return true;
      } else if (var3 == null) {
         return false;
      } else {
         for (int var4 = 0; var4 < var3.length; var4++) {
            int var5 = var2.size() - 1;
            if (var2.get(var5) instanceof IEJump || var2.get(var5) instanceof IEJumpFar) {
               var5--;
            }

            for (int var6 = var5; var6 >= 0; var6--) {
               IEStatement var7 = (IEStatement)var2.get(var6);
               if (!(var7 instanceof IENop)) {
                  if (var7 instanceof IEUntranslatedInstruction) {
                     if (isExpressionModified(var7, var3[var4], true)) {
                        return false;
                     }
                  } else {
                     if (!(var7 instanceof IEAssign)) {
                        return false;
                     }

                     IEAssign var8 = var7.asAssign();
                     if (!var8.getLeftOperand().isVar()) {
                        var3[var4] = replaceSubExpressionRecursive(var3[var4], var8.getLeftOperand(), var8.getRightOperand().duplicate());
                        if (isExpressionModified(var7, var3[var4], false)) {
                           return false;
                        }
                     } else {
                        IEGeneric var9 = var8.getRightOperand();
                        if (var3[var4].equals(var8.getLeftOperand())) {
                           var3[var4] = var9;
                        } else {
                           if (var1.getStackPointer().equals(var8.getLeftOperand())) {
                              for (int var12 : var3[var4].getUsed().getVarIds()) {
                                 if (var1.getStackPointer().getId() == var12) {
                                    return false;
                                 }
                              }
                           }

                           var3[var4].replaceVar(var8.getLeftOperand().asVar(), var9.duplicate());
                        }
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public static boolean isExpressionModified(IEStatement var0, IEGeneric var1, boolean var2) {
      ArrayList var3;
      ArrayList var4;
      try {
         var3 = new ArrayList();
         var4 = new ArrayList();
         var0.getDefUse(var3, var4, null);
         if (var3.isEmpty() && var4.isEmpty()) {
            return false;
         }
      } catch (Exception var11) {
         return true;
      }

      Iterator var6;
      try {
         List var5 = var1.getUsed().getVarIds();
         var6 = var5.iterator();
      } catch (Exception var9) {
         return true;
      }

      while (true) {
         int var7;
         try {
            if (!var6.hasNext()) {
               return false;
            }

            var7 = (Integer)var6.next();
            if (var3.contains(var7)) {
               return true;
            }
         } catch (Exception var8) {
            return true;
         }

         try {
            if (var2 && var4.contains(var7)) {
               return true;
            }
         } catch (Exception var10) {
            return true;
         }
      }
   }

   public static boolean isMatchDuaryOperation(IEGeneric var0, OperationType var1, IEGeneric var2, IEGeneric var3) {
      if (isOperation(var0, var1)) {
         IEGeneric var4 = var0.asOperation().getOperand1();
         if (var4 == null && var2 == null || var4 != null && var4.equals(var2)) {
            IEGeneric var5 = var0.asOperation().getOperand2();
            if (var5 == null && var3 == null || var5 != null && var5.equals(var3)) {
               return true;
            }
         }
      }

      return false;
   }

   public static IEMem getAssigningToMemory(IEStatement var0) {
      return var0 instanceof IEAssign && var0.asAssign().getDstOperand().isMem() ? var0.asAssign().getDstOperand().asMem() : null;
   }

   public static IEMem getAssigningFromMemory(IEStatement var0) {
      return var0 instanceof IEAssign && var0.asAssign().getSrcOperand().isMem() ? var0.asAssign().getSrcOperand().asMem() : null;
   }

   public static boolean hasLinearControlFlow(IERoutineContext var0) {
      for (BasicBlock var2 : var0.getCfg().getBlocks()) {
         if (var2.allinsize() > 1 || var2.alloutsize() > 1) {
            return false;
         }
      }

      return true;
   }

   public static List getVars(IERoutineContext var0, Collection var1) {
      ArrayList var2 = new ArrayList(var1.size());

      for (int var4 : var1) {
         IEVar var5 = var0.getVariableById(var4);
         if (var5 == null) {
            throw new IllegalArgumentException("Unknown variable id: " + var4);
         }

         var2.add(var5);
      }

      return var2;
   }

   public static String formatVars(IERoutineContext var0, Collection var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (int var5 : var1) {
         if (var3 > 0) {
            var2.append(", ");
         }

         if (var0 != null) {
            IEVar var6 = var0.getVariableById(var5);
            if (var6 == null) {
               throw new IllegalArgumentException("Unknown variable id: " + var5);
            }

            var2.append(var6.getName());
         } else {
            var2.append(var5);
         }

         var3++;
      }

      return var2.toString();
   }

   public static String formatVars(IERoutineContext var0, int... var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (int var7 : var1) {
         if (var3 > 0) {
            var2.append(", ");
         }

         if (var0 != null) {
            IEVar var8 = var0.getVariableById(var7);
            if (var8 == null) {
               throw new IllegalArgumentException("Unknown variable id: " + var7);
            }

            var2.append(var8.getName());
         } else {
            var2.append(var7);
         }

         var3++;
      }

      return var2.toString();
   }

   public static boolean isVariableAssigned(IEStatement var0, IEVar var1) {
      if (!(var0 instanceof IEAssign)) {
         return false;
      } else {
         IEGeneric var2 = var0.asAssign().getDstOperand();
         return var2 == var1 || var2 instanceof IESlice && var2.asSlice().getWholeExpression() == var1;
      }
   }

   public static boolean hasTypeInfo(IEGeneric var0) {
      return var0.getType() != null && !var0.getType().isUndefined();
   }

   public static boolean hasNoTypeInfo(IEGeneric var0) {
      return var0.getType() == null || var0.getType().isUndefined();
   }

   public static boolean hasSameType(IEGeneric var0, IEGeneric var1) {
      return isSameType(var0.getType(), var1.getType());
   }

   public static boolean isSameType(IWildcardType var0, IWildcardType var1) {
      if (var0 != null && !var0.isUndefined() || var1 != null && !var1.isUndefined()) {
         if (var0 == null && var1 != null) {
            return false;
         } else {
            return var0 != null && var1 == null ? false : var0.resolveA().equals(var1.resolveA());
         }
      } else {
         return true;
      }
   }

   public static IWildcardType getBestType(IWildcardType var0, IWildcardType var1) {
      if (var0 != null) {
         if (var1 != null) {
            int var2 = var0.compareTo(var1);
            if (var2 > 0) {
               return var0;
            } else {
               return var2 < 0 ? var1 : null;
            }
         } else {
            return var0;
         }
      } else {
         return var1;
      }
   }

   public static boolean requiresExplicitCast(IWildcardType var0, IWildcardType var1) {
      if (var0 != null && !var0.isUndefined() || var1 != null && !var1.isUndefined()) {
         if (var0 == null && var1 != null) {
            return true;
         } else if (var0 != null && var1 == null) {
            return true;
         } else {
            var0 = var0.resolveA();
            var1 = var1.resolveA();
            if (var0.equals(var1)) {
               return false;
            } else {
               if (var0 != null && var0.isResolved() && var1 != null && var1.isResolved()) {
                  INativeType var2 = var0.getNativeType();
                  INativeType var3 = var1.getNativeType();
                  var2 = TypeUtil.getNonAlias(var2);
                  var3 = TypeUtil.getNonAlias(var3);
                  if (var2.equals(var3)) {
                     return false;
                  }

                  if (var3 instanceof IReferenceType && var2.getName().equals("void*")) {
                     return false;
                  }

                  int var4 = 10;

                  while (var4-- > 0 && var2 instanceof IReferenceType && var3 instanceof IReferenceType) {
                     IReferenceType var5 = (IReferenceType)var2;
                     IReferenceType var6 = (IReferenceType)var3;
                     if (var5.getReferenceCount() != var6.getReferenceCount()) {
                        break;
                     }

                     INativeType var7 = var5.getReferencedType();
                     INativeType var8 = var6.getReferencedType();
                     var2 = TypeUtil.getNonAlias(var7);
                     var3 = TypeUtil.getNonAlias(var8);
                     if (var2.equals(var3)) {
                        return false;
                     }
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   public static List getWildcardTypes(IWildcardTypeManager var0, Collection var1) {
      ArrayList var2 = new ArrayList(var1.size());

      for (IEGeneric var4 : var1) {
         var2.add(var4.getSafeType(var0));
      }

      return var2;
   }

   public static List gatherArgumentTypes(IWildcardPrototype var0, Collection var1) {
      ArrayList var2 = new ArrayList(var0.getParameterTypes());
      if (var0.isVariableArgument() && var1 != null) {
         var2.addAll(var1);
      }

      return var2;
   }

   public static int determineArgumentStackSlotCount(IWildcardPrototype var0, Collection var1) {
      ICallingConvention var2 = var0.getCallingConvention();
      int var3 = 0;
      IStorageEntryGenerator var4 = var2.getInputsGenerator();

      for (IWildcardType var6 : gatherArgumentTypes(var0, var1)) {
         StorageEntry var7 = var4.next(var6.getLayoutInfo());
         if (var7.getType() == StorageEntry.Type.STACK) {
            var3 += var6.getSlotCount();
         }
      }

      return var3;
   }

   public static int determineReturnValuesStackSlotCount(IWildcardPrototype var0, int var1) {
      ICallingConvention var2 = var0.getCallingConvention();
      int var3 = 0;
      IStorageEntryGenerator var4 = var2.getOutputsGenerator(var1);

      for (IWildcardType var6 : var0.getReturnTypes()) {
         StorageEntry var7 = var4.next(var6.getLayoutInfo());
         if (var7.getType() == StorageEntry.Type.STACK) {
            var3 += var7.getSlotCount();
         }
      }

      return var3;
   }

   public static IEGeneric getAssignmentSource(IEStatement var0) {
      return !(var0 instanceof IEAssign) ? null : var0.asAssign().getSrcOperand();
   }

   public static IEGeneric getAssignmentDestination(IEStatement var0) {
      return !(var0 instanceof IEAssign) ? null : var0.asAssign().getDstOperand();
   }

   public static boolean hasNoSideEffect(IEGeneric var0) {
      return !(var0 instanceof IEStatement);
   }

   public static boolean hasSideEffect(IEGeneric var0) {
      return !hasNoSideEffect(var0);
   }

   public static Boolean checkCallReturnAddress(IERoutineContext var0, CFG var1, BasicBlock var2, int var3) {
      IECall var4 = (IECall)var2.get(var3);
      IEGeneric var5 = var4.getReturnLocation();
      if (var5 == null) {
         return null;
      } else {
         Long var6 = null;
         if (var5 instanceof IEImm var7) {
            if (var7.canReadAsAddress()) {
               var6 = var7.getValueAsAddress();
            }
         } else if (var5 instanceof IEVar var14 && var14.isGlobalReference()) {
            var6 = var14.getAddress();
         }

         if (var6 != null) {
            long var15 = var2.getAddressOfInstruction(var3);
            long var9 = var15 + var4.getSize();
            IEStatement var11 = (IEStatement)var1.getInstruction(var9);
            if (var11 != null) {
               long var12 = var0.getConverter().sanitizeNativeAddress(var6);
               if (var11.getLowerLevelAddresses().contains(var12)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static CFG expandStatementSize(IERoutineContext var0, IEStatement var1, int var2) {
      CFG var3 = var0.getCfg();
      if (var1.getSize() < var2) {
         EUtil$7 var4 = new EUtil$7(var0, var1, var2);
         if (var4.process(false, false, true)) {
            var3 = var4.getCfg();
         }
      }

      return var3;
   }

   public static CFG expandStatementSizes(IERoutineContext var0, Collection var1, int var2) {
      CFG var3 = var0.getCfg();
      Set var4 = (Set)var1.stream().filter(var1x -> var1x.getSize() < var2).collect(Collectors.toSet());
      if (!var4.isEmpty()) {
         EUtil$8 var5 = new EUtil$8(var0, var4, var2);
         if (var5.process(false, false, true)) {
            var3 = var5.getCfg();
         }
      }

      return var3;
   }

   public static int adjustBranchToIRInstruction(IEStatement var0, int var1, int var2) {
      int var3 = 0;
      if (var0.isJump()) {
         IEJump var4 = var0.asJump();
         if (var4.getBranchAddress() == var1) {
            var4.setBranchAddress(var2);
            var3++;
         }
      } else if (var0.isSwitch()) {
         IESwitch var7 = var0.asSwitch();
         if (var7.getDefaultAddress() == var1) {
            var7.setDefaultAddress(var2);
            var3++;
         }

         for (Couple var6 : var7.getCases()) {
            if ((Integer)var6.getSecond() == var1) {
               var6.setSecond(var2);
               var3++;
            }
         }
      }

      return var3;
   }

   public static boolean expandCalls(IERoutineContext var0, boolean var1) {
      boolean var2 = false;

      for (IEStatement var4 : var0.getCfg().instructions()) {
         if (var4.getSize() == 1 && (var4 instanceof IECall || var1 && isPCAssign(var4))) {
            var2 = true;
            break;
         }
      }

      if (!var2) {
         return true;
      } else {
         EUtil$9 var5 = new EUtil$9(var0, var1);
         return var5.process(false, false, true);
      }
   }

   public static void setLowerLevelAddress(long var0, List var2) {
      var2.forEach(var2x -> var2x.setLowerLevelAddress(var0));
   }

   public static void setLowerLevelAddress(long var0, List var2, int var3, int var4) {
      var2.subList(var3, var4).forEach(var2x -> var2x.setLowerLevelAddress(var0));
   }

   public static void setLowerLevelAddressIfNone(long var0, List var2) {
      var2.forEach(var2x -> {
         if (var2x.getLowerLevelAddresses().isEmpty()) {
            var2x.setLowerLevelAddress(var0);
         }
      });
   }

   public static void setLowerLevelAddressIfNone(long var0, List var2, int var3, int var4) {
      var2.subList(var3, var4).forEach(var2x -> {
         if (var2x.getLowerLevelAddresses().isEmpty()) {
            var2x.setLowerLevelAddress(var0);
         }
      });
   }

   public static boolean isLegalSignedImmediate(IEImm var0, int var1) {
      int var2 = var0.getBitsize();
      if (var1 <= 0 || var1 > var2) {
         return false;
      } else if (var1 == var2) {
         return true;
      } else {
         IEImm var3 = var0._sar(var1 - 1);
         return var3.isZero() || var3.isOnes();
      }
   }

   public static boolean isLegalUnsignedImmediate(IEImm var0, int var1) {
      int var2 = var0.getBitsize();
      if (var1 <= 0 || var1 > var2) {
         return false;
      } else if (var1 == var2) {
         return true;
      } else {
         IEImm var3 = var0._shr(var1);
         return var3.isZero();
      }
   }
}
