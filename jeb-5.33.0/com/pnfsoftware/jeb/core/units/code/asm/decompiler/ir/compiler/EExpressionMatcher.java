package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EExpressionMatcher {
   private static final ILogger logger = GlobalLog.getLogger(EExpressionMatcher.class);
   public static boolean verbose = false;
   private static int commuteForkMode = 2;
   private static int MAX_FORK_COUNT = 50;
   private INode template;
   private Map map;
   private int state;
   private int forkcount;
   private boolean allowDeepAssociativity = false;
   private Map IRDepthsMap;
   private static final BigInteger MINUS_ONE = BigInteger.valueOf(-1L);

   public EExpressionMatcher(INode var1, Map var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.template = var1;
         if (var1 instanceof Node var3 && var3.descMaxDepth < 0) {
            Util.calculateProperties(var1);
         }

         if (var2 == null) {
            var2 = new HashMap();
         }

         this.map = (Map)var2;
      }
   }

   public EExpressionMatcher(INode var1) {
      this(var1, null);
   }

   private EExpressionMatcher() {
   }

   private boolean canFork() {
      return this.forkcount < MAX_FORK_COUNT;
   }

   private EExpressionMatcher fork(EExpressionMatcher var1) {
      EExpressionMatcher var2 = new EExpressionMatcher();
      var2.template = this.template;
      var2.map = new HashMap(this.map);
      var2.state = this.state;
      var2.forkcount = this.forkcount + 1;
      var2.allowDeepAssociativity = this.allowDeepAssociativity;
      return var2;
   }

   public void setAllowDeepAssociativity(boolean var1) {
      this.allowDeepAssociativity = var1;
   }

   public boolean isAllowDeepAssociativity() {
      return this.allowDeepAssociativity;
   }

   public void setIRDepthsMap(Map var1) {
      this.IRDepthsMap = var1;
   }

   public Map getIRDepthsMap() {
      return this.IRDepthsMap;
   }

   public Map getMatchMap() {
      return this.map;
   }

   public void reset(boolean var1) {
      if (!var1) {
         this.map.clear();
      }

      this.state = 0;
      this.forkcount = 0;
   }

   public void reset() {
      this.reset(false);
   }

   public boolean isMatch(IEGeneric var1) {
      if (this.state != 0) {
         throw new IllegalStateException();
      } else {
         this.state = 1;
         return this.isEqual(var1, this.template, 0);
      }
   }

   private static boolean failed() {
      return false;
   }

   private static boolean failed(O var0, IEGeneric var1) {
      return false;
   }

   private static boolean failed(String var0) {
      return false;
   }

   private boolean isEqual(IEGeneric var1, INode var2, int var3) {
      if (var2 instanceof Node var14) {
         if (this.IRDepthsMap != null) {
            Integer var15 = (Integer)this.IRDepthsMap.get(var1);
            if (var15 != null && var14.descMaxDepth > var15) {
               return false;
            }
         }

         O var16 = var14.operator;
         if (var16 == null) {
            if (!(var1 instanceof IEOperation)) {
               return failed("Expected an EOperation");
            }

            OperationType var20 = ((IEOperation)var1).getOperationType();

            for (O var10 : var14.opgrp.operators) {
               if (var10.getOperationType() == var20) {
                  var16 = var10;
                  int var11 = var14.opgrp.id;
                  Object var12 = this.map.get(var11);
                  if (var12 != null) {
                     if (var12 != var20) {
                        return failed("Leaf mismatched");
                     }
                  } else {
                     this.map.put(var11, var20);
                  }
                  break;
               }
            }

            if (var16 == null) {
               return failed("Null operator");
            }
         }

         if (var14.opnds.length >= 3) {
            switch (var16) {
               case ADD:
                  if (!var1.isOperation(OperationType.ADD, OperationType.SUB)) {
                     return failed(var16, var1);
                  }

                  List var37 = this.collectAdds((IEOperation)var1);
                  if (var37.size() != var14.opnds.length) {
                     return failed(var16, var1);
                  }

                  if (!this.checkSequence(var37, var14.opnds, 0, var3)) {
                     return failed(var16, var1);
                  }

                  return true;
               case MUL:
                  if (!var1.isOperation(OperationType.MUL)) {
                     return failed(var16, var1);
                  }

                  List var36 = this.collectMuls((IEOperation)var1);
                  if (var36.size() != var14.opnds.length) {
                     return failed(var16, var1);
                  }

                  if (!this.checkSequence(var36, var14.opnds, 0, var3)) {
                     return failed(var16, var1);
                  }

                  return true;
               case AND:
               case OR:
               case XOR:
                  if (!var1.isOperation(OperationType.AND, OperationType.OR, OperationType.XOR)) {
                     return failed(var16, var1);
                  }

                  List var35 = this.collectBitwise((IEOperation)var1);
                  if (var35.size() != var14.opnds.length) {
                     return failed(var16, var1);
                  }

                  if (!this.checkSequence(var35, var14.opnds, 0, var3)) {
                     return failed(var16, var1);
                  }

                  return true;
            }
         }

         switch (var16) {
            case SLICE:
               if (var1 instanceof IESlice) {
                  int var34 = ((Leaf)var14.opnds[1]).value.intValue();
                  int var49 = ((Leaf)var14.opnds[2]).value.intValue();
                  if (var34 == ((IESlice)var1).getBitStart() && var49 == ((IESlice)var1).getBitEnd()) {
                     IEGeneric var52 = ((IESlice)var1).getWholeExpression();
                     return this.isEqual(var52, var14.opnds[0], var3 + 1);
                  }
               }

               return failed(var16, var1);
            case SLICE_FIRSTBIT:
               if (var1 instanceof IESlice) {
                  IERange var33 = ((IESlice)var1).getRange();
                  if (var33.getRangeLength() == 1 && var33.getBegin() == 0) {
                     IEGeneric var48 = ((IESlice)var1).getWholeExpression();
                     return this.isEqual(var48, var14.opnds[0], var3 + 1);
                  }
               }

               return failed(var16, var1);
            case SLICE_LASTBIT:
               if (var1 instanceof IESlice) {
                  IEGeneric var32 = ((IESlice)var1).getWholeExpression();
                  IERange var47 = ((IESlice)var1).getRange();
                  if (var47.getRangeLength() == 1 && var47.getEnd() == var32.getBitsize()) {
                     return this.isEqual(var32, var14.opnds[0], var3 + 1);
                  }
               }

               return failed(var16, var1);
            case SLICE_HALFBIT:
               if (var1 instanceof IESlice) {
                  IEGeneric var31 = ((IESlice)var1).getWholeExpression();
                  IERange var46 = ((IESlice)var1).getRange();
                  if (var46.getRangeLength() == 1 && var46.getEnd() == var31.getBitsize() / 2) {
                     return this.isEqual(var31, var14.opnds[0], var3 + 1);
                  }
               }

               return failed(var16, var1);
            case SLICE_FIRST32:
               if (var1 instanceof IESlice) {
                  IEGeneric var30 = ((IESlice)var1).getWholeExpression();
                  IERange var45 = ((IESlice)var1).getRange();
                  if (var45.getRangeLength() == 32 && var45.getBegin() == 0) {
                     return this.isEqual(var30, var14.opnds[0], var3 + 1);
                  }
               }

               return failed(var16, var1);
            case SLICE_HALF1:
               if (var1 instanceof IESlice) {
                  IEGeneric var29 = ((IESlice)var1).getWholeExpression();
                  IERange var44 = ((IESlice)var1).getRange();
                  if (var44.getRangeLength() == var29.getBitsize() / 2 && var44.getBegin() == 0) {
                     return this.isEqual(var29, var14.opnds[0], var3 + 1);
                  }
               }

               return failed(var16, var1);
            case SLICE_HALF2:
               if (var1 instanceof IESlice) {
                  IEGeneric var28 = ((IESlice)var1).getWholeExpression();
                  IERange var43 = ((IESlice)var1).getRange();
                  if (var43.getRangeLength() == var28.getBitsize() / 2 && var43.getEnd() == var28.getBitsize()) {
                     return this.isEqual(var28, var14.opnds[0], var3 + 1);
                  }
               }

               return failed(var16, var1);
            case COMPOSE_2:
               if (var1 instanceof IECompose var27 && var27.getPartsCount() == 2) {
                  return this.isEqual(var27.getLowPart(), var14.opnds[0], var3 + 1) && this.isEqual(var27.getHighPart(), var14.opnds[1], var3 + 1);
               }

               return failed(var16, var1);
            case COMPOSE_2EQ:
               if (var1 instanceof IECompose var26 && var26.getPartsCount() == 2 && var26.getLowPart().getBitsize() == var26.getHighPart().getBitsize()) {
                  return this.isEqual(var26.getLowPart(), var14.opnds[0], var3 + 1) && this.isEqual(var26.getHighPart(), var14.opnds[1], var3 + 1);
               }

               return failed(var16, var1);
            case COND:
               if (!(var1 instanceof IECond var25)) {
                  return failed(var16, var1);
               }

               return this.isEqual(var25.getCondition(), var14.opnds[0], var3 + 1)
                  && this.isEqual(var25.getExpressionTrue(), var14.opnds[1], var3 + 1)
                  && this.isEqual(var25.getExpressionFalse(), var14.opnds[2], var3 + 1);
            case TRN:
            case TRN8:
            case TRN16:
            case TRN32:
            case TRN64:
            case TRN128:
               if (var1 instanceof IEOperation && ((IEOperation)var1).getOperationType() == OperationType.CAST) {
                  int var24 = var16.getResultingBitsize();
                  if (var24 != 0 && var24 != var1.getBitsize()) {
                     return failed("Unexpected bitsize");
                  }

                  IEGeneric var42 = ((IEOperation)var1).getOperand1();
                  return this.isEqual(var42, var14.opnds[0], var3 + 1);
               }

               return failed(var16, var1);
            case EXT:
            case EXT8:
            case EXT16:
            case EXT32:
            case EXT64:
            case EXT128:
               if (var1 instanceof IEOperation && ((IEOperation)var1).getOperationType() == OperationType.CAST_S) {
                  int var23 = var16.getResultingBitsize();
                  if (var23 != 0 && var23 != var1.getBitsize()) {
                     return failed("Unexpected bitsize");
                  }

                  IEGeneric var41 = ((IEOperation)var1).getOperand1();
                  return this.isEqual(var41, var14.opnds[0], var3 + 1);
               }

               return failed(var16, var1);
            default:
               if (var1 instanceof IEImm && var16 == O.NOT) {
                  IEImm var22 = var1.asImm()._not();
                  return this.isEqual(var22, var14.opnds[0], var3 + 1);
               }

               if (var1 instanceof IEOperation var21) {
                  OperationType var40 = var21.getOperationType();
                  boolean var51 = compareOperator(var40, var16);
                  if (!var51) {
                     if (var21.getOperand2() instanceof IEImm) {
                        if (var40 == OperationType.ADD && var16 == O.SUB || var40 == OperationType.SUB && var16 == O.ADD) {
                           List var57 = EUtil.getSubExpressions(var21);
                           var57.set(1, ((IEGeneric)var57.get(1)).asImm()._neg());
                           return this.isEqual((IEGeneric)var57.get(0), var14.opnds[0], var3 + 1)
                              && this.isEqual((IEGeneric)var57.get(1), var14.opnds[1], var3 + 1);
                        }

                        if (var40 == OperationType.SHL && var16 == O.MUL) {
                           IEImm var54 = var21.getOperand2().asImm();
                           if (var54.canReadAsLong()) {
                              int var61 = (int)var54.getValueAsLong();
                              IEImm var65 = EUtil.imm(2L, var21.getBitsize())._pow(var61);
                              return this.isEqual(var21.getOperand1(), var14.opnds[0], var3 + 1) && this.isEqual(var65, var14.opnds[1], var3 + 1);
                           }
                        } else if (var16 == O.SHL && var40 == OperationType.MUL) {
                           IEImm var53 = var21.getOperand2().asImm();
                           Integer var58 = var53._log2();
                           if (var58 != null) {
                              IEImm var62 = EUtil.imm(var58.intValue(), var21.getBitsize());
                              return this.isEqual(var21.getOperand1(), var14.opnds[0], var3 + 1) && this.isEqual(var62, var14.opnds[1], var3 + 1);
                           }
                        }
                     }
                     if (switch (var16) {
                        case GE_S -> var40 == OperationType.LE_S;
                        case GT_S -> var40 == OperationType.LT_S;
                        case LE_S -> var40 == OperationType.GE_S;
                        case LT_S -> var40 == OperationType.GT_S;
                        case GE_U -> var40 == OperationType.LE_U;
                        case GT_U -> var40 == OperationType.LT_U;
                        case LE_U -> var40 == OperationType.GE_U;
                        case LT_U -> var40 == OperationType.GT_U;
                        default -> false;
                     }) {
                        return this.isEqual(var21.getOperand2(), var14.opnds[0], var3 + 1) && this.isEqual(var21.getOperand1(), var14.opnds[1], var3 + 1);
                     }
                  }

                  if (var51) {
                     List var56 = EUtil.getSubExpressions(var21);
                     Boolean var59 = this.checkCommutative(var56, var14, var16, var3);
                     if (var59 != null) {
                        if (!var59) {
                           Boolean var64 = this.checkAssociative(var21, var14, var16, var3);
                           if (var64 != null) {
                              return var64;
                           }
                        }

                        return var59;
                     }

                     var59 = this.checkAssociative(var21, var14, var16, var3);
                     if (var59 != null) {
                        return var59;
                     }

                     int var63 = 0;

                     for (IEGeneric var13 : var56) {
                        if (!this.isEqual(var13, var14.opnds[var63], var3 + 1)) {
                           return failed("Operand mismatch: " + var13);
                        }

                        var63++;
                     }

                     return true;
                  }
               }
         }
      } else {
         Leaf var4 = (Leaf)var2;
         if (var4.optionalBitsize != 0 && var4.optionalBitsize != var1.getBitsize()) {
            return failed("leaf: unexpected bitsize");
         }

         boolean var5 = var1 instanceof IEVar || var1 instanceof IEImm || var1 instanceof IERange;
         if ((var4.flags & 15) == 15
            || var1 instanceof IEVar && (var4.flags & 2) == 2
            || var1 instanceof IEImm && (var4.flags & 1) == 1
            || var1 instanceof IERange && (var4.flags & 4) == 4
            || var5 && (var4.flags & 7) == 7
            || !var5 && (var4.flags & 8) == 8) {
            if (var4.handler != null) {
               var1 = var4.handler.process(var4, var1);
               if (var1 == null) {
                  return failed("leaf: process() returned null");
               }
            }

            IEGeneric var19 = (IEGeneric)this.map.get(var4.id);
            if (var19 != null) {
               return this.compareAndUpdate(var19, var1, var4, true);
            }

            this.map.put(var4.id, var1);
            return true;
         }

         if (var1 instanceof IEImm && (var4.flags & 16) != 0) {
            if (var4.id >= 0) {
               IEGeneric var17 = (IEGeneric)this.map.get(var4.id);
               if (var17 != null) {
                  return this.compareAndUpdate(var17, var1, var4, true);
               }

               this.map.put(var4.id, var1);
            }

            BigInteger var18 = var4.value;
            BigInteger var38 = ((IEImm)var1).getValue();
            if (var38.compareTo(var18) == 0) {
               return true;
            }

            if (var1.getBitsize() == 1 && var38.equals(MINUS_ONE)) {
               return BigInteger.ONE.compareTo(var4.value) == 0;
            }
         } else if (var1 instanceof IEImm && (var4.flags & 32) != 0) {
            IEGeneric var6 = (IEGeneric)this.map.get(var4.id);
            if (var6 == null) {
               return false;
            }

            BigInteger var7 = BigInteger.valueOf(var6.getBitsize() - 1);
            BigInteger var8 = ((IEImm)var1).getValue();
            return var8.compareTo(var7) == 0;
         }
      }

      return failed();
   }

   private boolean compareAndUpdate(IEGeneric var1, IEGeneric var2, Leaf var3, boolean var4) {
      if (var1.equals(var2)) {
         return true;
      } else if (var1.equalsEx(var2, false)) {
         if (var2.getType() == null) {
            return true;
         } else if (var1.getType() == null) {
            if (var3.id >= 0 && var4) {
               this.map.put(var3.id, var2);
            }

            return true;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   static boolean compareOperator(OperationType var0, O var1) {
      switch (var1) {
         case MUL:
            return var0 == OperationType.MUL;
         case DIV:
            return var0 == OperationType.DIV_U || var0 == OperationType.DIV_S;
         case REM:
            return var0 == OperationType.REM_U || var0 == OperationType.REM_S;
         case NCADD:
            return var0 == OperationType.ADD || var0 == OperationType.XOR || var0 == OperationType.OR;
         case NCSUB:
            return var0 == OperationType.SUB || var0 == OperationType.XOR;
         default:
            return var1.getOperationType() == var0;
      }
   }

   List collectAdds(IEOperation var1) {
      ArrayList var2 = new ArrayList();
      this.collectAddsRecurse(var1, false, var2);
      return var2;
   }

   private void collectAddsRecurse(IEGeneric var1, boolean var2, List var3) {
      if (var1 instanceof IEOperation) {
         IEOperation var4 = var1.asOperation();
         OperationType var5 = var4.getOperationType();
         if (var5 == OperationType.ADD) {
            this.collectAddsRecurse(var4.getOperand1(), var2, var3);
            this.collectAddsRecurse(var4.getOperand2(), var2, var3);
         } else if (var5 == OperationType.SUB) {
            this.collectAddsRecurse(var4.getOperand1(), var2, var3);
            this.collectAddsRecurse(var4.getOperand2(), !var2, var3);
         } else {
            var3.add(new EExpressionMatcher.SeqEntry(var1, var2));
         }
      } else if (!(var1 instanceof IEImm) || !((IEImm)var1).isZero()) {
         var3.add(new EExpressionMatcher.SeqEntry(var1, var2));
      }
   }

   List collectMuls(IEOperation var1) {
      ArrayList var2 = new ArrayList();
      this.collectMulsRecurse(var1, false, var2);
      return var2;
   }

   private void collectMulsRecurse(IEGeneric var1, boolean var2, List var3) {
      if (var1 instanceof IEOperation) {
         IEOperation var4 = var1.asOperation();
         OperationType var5 = var4.getOperationType();
         if (var5 == OperationType.MUL) {
            this.collectMulsRecurse(var4.getOperand1(), var2, var3);
            this.collectMulsRecurse(var4.getOperand2(), var2, var3);
         } else if (var5 == OperationType.SUB && var4.getOperand1().isImm() && var4.getOperand1().asImm().isZero()) {
            this.collectMulsRecurse(var4.getOperand2(), !var2, var3);
         } else {
            var3.add(new EExpressionMatcher.SeqEntry(var1, var2));
         }
      } else {
         var3.add(new EExpressionMatcher.SeqEntry(var1, var2));
      }
   }

   List collectBitwise(IEOperation var1) {
      ArrayList var2 = new ArrayList();
      this.collectBitwiseRecurse(var1, var1.getOperationType(), var2);
      return var2;
   }

   private void collectBitwiseRecurse(IEGeneric var1, OperationType var2, List var3) {
      if (var1 instanceof IEOperation) {
         IEOperation var4 = var1.asOperation();
         OperationType var5 = var4.getOperationType();
         if (var5 == var2) {
            this.collectBitwiseRecurse(var4.getOperand1(), var2, var3);
            this.collectBitwiseRecurse(var4.getOperand2(), var2, var3);
         } else {
            var3.add(new EExpressionMatcher.SeqEntry(var1));
         }
      } else {
         var3.add(new EExpressionMatcher.SeqEntry(var1));
      }
   }

   boolean checkSequence(List var1, INode[] var2, int var3, int var4) {
      if (var1.isEmpty()) {
         return true;
      } else {
         INode var5 = var2[var3];

         for (int var6 = 0; var6 < var1.size(); var6++) {
            EExpressionMatcher.SeqEntry var7 = (EExpressionMatcher.SeqEntry)var1.get(var6);
            IEGeneric var8 = var7.exp;
            if (var7.neg) {
               if (!(var5 instanceof Node) || ((Node)var5).operator != O.NEG) {
                  continue;
               }

               var5 = ((Node)var5).opnds[0];
            }

            if (!this.canFork()) {
               return false;
            }

            EExpressionMatcher var9 = this.fork(this);
            boolean var10 = var9.isEqual(var8, var5, var4 + 1);
            this.forkcount = var9.forkcount;
            if (var10) {
               ArrayList var11 = new ArrayList(var1);
               var11.remove(var6);
               var10 = var9.checkSequence(var11, var2, var3 + 1, var4 + 1);
               this.forkcount = var9.forkcount;
               if (var10) {
                  this.map.putAll(var9.map);
                  return true;
               }
            }
         }

         return false;
      }
   }

   private Boolean checkCommutative(List var1, Node var2, O var3, int var4) {
      if (commuteForkMode > 0 && var3.isCommutative()) {
         if (var2.opnds[0] instanceof Leaf var5 && var2.opnds[1] instanceof Leaf var6) {
            boolean var14 = false;
            int var16 = var5.id;
            int var19 = var6.id;
            IEGeneric var21 = (IEGeneric)this.map.get(var16);
            IEGeneric var22 = (IEGeneric)this.map.get(var19);
            if (var21 != null && var22 == null) {
               if (var21.equalsEx(var1.get(1), false)) {
                  var14 = true;
               }
            } else if (var21 == null && var22 != null) {
               if (var22.equalsEx(var1.get(0), false)) {
                  var14 = true;
               }
            } else if (var21 != null && var22 != null && (!var21.equalsEx(var1.get(0), false) || !var22.equalsEx(var1.get(1), false))) {
               var14 = true;
            }

            if (var14) {
               return this.isEqual((IEGeneric)var1.get(0), var2.opnds[1], var4 + 1) && this.isEqual((IEGeneric)var1.get(1), var2.opnds[0], var4 + 1);
            }

            return this.isEqual((IEGeneric)var1.get(0), var2.opnds[0], var4 + 1) && this.isEqual((IEGeneric)var1.get(1), var2.opnds[1], var4 + 1);
         }

         if (commuteForkMode >= 2 && this.canFork()) {
            int var13 = var2.opnds[0] instanceof Node var8 ? var8.score : 1;
            int var15 = var2.opnds[1] instanceof Node var17 ? var17.score : 1;
            boolean var20 = false;
            EExpressionMatcher var11 = this.fork(this);
            boolean var18;
            if (var15 < var13) {
               var18 = var11.isEqual((IEGeneric)var1.get(1), var2.opnds[1], var4 + 1);
               this.forkcount = var11.forkcount;
               if (var18) {
                  var20 = var11.isEqual((IEGeneric)var1.get(0), var2.opnds[0], var4 + 1);
                  this.forkcount = var11.forkcount;
               }
            } else {
               var18 = var11.isEqual((IEGeneric)var1.get(0), var2.opnds[0], var4 + 1);
               this.forkcount = var11.forkcount;
               if (var18) {
                  var20 = var11.isEqual((IEGeneric)var1.get(1), var2.opnds[1], var4 + 1);
                  this.forkcount = var11.forkcount;
               }
            }

            if (!var20) {
               var11 = this.fork(this);
               if (var15 < var13) {
                  var18 = var11.isEqual((IEGeneric)var1.get(0), var2.opnds[1], var4 + 1);
                  this.forkcount = var11.forkcount;
                  if (var18) {
                     var20 = var11.isEqual((IEGeneric)var1.get(1), var2.opnds[0], var4 + 1);
                     this.forkcount = var11.forkcount;
                  }
               } else {
                  var18 = var11.isEqual((IEGeneric)var1.get(1), var2.opnds[0], var4 + 1);
                  this.forkcount = var11.forkcount;
                  if (var18) {
                     var20 = var11.isEqual((IEGeneric)var1.get(0), var2.opnds[1], var4 + 1);
                     this.forkcount = var11.forkcount;
                  }
               }
            }

            if (var18 && var20) {
               this.map.putAll(var11.map);
               return true;
            }

            return failed("Operand mismatch: " + var1.get(1));
         }
      }

      return null;
   }

   private Boolean checkAssociative(IEOperation var1, Node var2, O var3, int var4) {
      if (this.allowDeepAssociativity && var3.isAssociative() && this.canFork()) {
         List var5 = switch (var3.getOperationType()) {
            case ADD -> this.collectAdds(var1);
            case MUL -> this.collectMuls(var1);
            default -> this.collectBitwise(var1);
         };
         if (var5 != null && var5.size() > 2 && var5.size() >= var2.opnds.length) {
            EExpressionMatcher var6 = this.fork(this);

            for (IEOperation var9 : this.buildAssociatives(var5, var3)) {
               ArrayList var10 = new ArrayList();
               var10.add(new EExpressionMatcher.SeqEntry(var9.getOperand1(), false));
               var10.add(new EExpressionMatcher.SeqEntry(var9.getOperand2(), false));
               if (var6.checkSequence(var10, var2.opnds, 0, var4)) {
                  this.map.putAll(var6.map);
                  return true;
               }
            }

            return null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private List buildAssociatives(List var1, O var2) {
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList(var1);
      ArrayList var5 = new ArrayList();
      var5.add(0, (EExpressionMatcher.SeqEntry)var4.remove(0));
      IEGeneric var6 = this.buildAssociativeOp(var5, var2);
      IEGeneric var7 = this.buildAssociativeOp(var4, var2);
      var3.add(EUtil.op(var2.getOperationType(), var6, var7));

      for (int var8 = 1; var8 < var1.size(); var8++) {
         for (int var9 = var8; var8 == 1 && var9 < var1.size() - 1 || var8 != 1 && var9 < var1.size(); var9++) {
            var4 = new ArrayList(var1);
            var5 = new ArrayList();

            for (int var10 = var9; var10 >= var8; var10--) {
               var5.add(0, (EExpressionMatcher.SeqEntry)var4.remove(var10));
            }

            var5.add(0, (EExpressionMatcher.SeqEntry)var4.remove(0));
            var6 = this.buildAssociativeOp(var5, var2);
            var7 = this.buildAssociativeOp(var4, var2);
            var3.add(EUtil.op(var2.getOperationType(), var6, var7));
         }
      }

      return var3;
   }

   private IEGeneric buildAssociativeOp(List var1, O var2) {
      if (var1.isEmpty()) {
         return null;
      } else {
         EExpressionMatcher.SeqEntry var4 = (EExpressionMatcher.SeqEntry)var1.get(0);
         Object var3;
         if (var4.neg) {
            var3 = EUtil.op(var2.getOperationType(), EUtil.imm(0L, var4.exp.getBitsize()), var4.exp);
         } else {
            var3 = var4.exp;
         }

         for (int var5 = 1; var5 < var1.size(); var5++) {
            EExpressionMatcher.SeqEntry var6 = (EExpressionMatcher.SeqEntry)var1.get(var5);
            if (var6.neg) {
               if (var2.getOperationType() == OperationType.ADD) {
                  var3 = EUtil.sub((IEGeneric)var3, var6.exp);
               } else {
                  var3 = EUtil.op(var2.getOperationType(), (IEGeneric)var3, EUtil.op(var2.getOperationType(), EUtil.imm(0L, var6.exp.getBitsize()), var6.exp));
               }
            } else {
               var3 = EUtil.op(var2.getOperationType(), (IEGeneric)var3, var6.exp);
            }
         }

         return (IEGeneric)var3;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("IR Matcher: '%s' [leafmap: %s] [fork: %d]", this.template, this.map, this.forkcount);
   }

   static class SeqEntry {
      IEGeneric exp;
      boolean neg;

      SeqEntry(IEGeneric var1) {
         this.exp = var1;
      }

      SeqEntry(IEGeneric var1, boolean var2) {
         this.exp = var1;
         this.neg = var2;
      }

      @Override
      public String toString() {
         return this.neg ? "-" + this.exp : this.exp.toString();
      }
   }
}
