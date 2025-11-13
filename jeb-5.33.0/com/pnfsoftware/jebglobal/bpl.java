package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class bpl {
   private static final ILogger pC = GlobalLog.getLogger(bpl.class);
   private static final Object A = new Object();
   private static final Object kS = new Object();
   private static final int[][] wS = new int[][]{
      {1, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 1, 0, 1, 1, 1, 1},
      {0, 0, 1, 0, 1, 1, 1, 1},
      {0, 0, 0, 1, 1, 1, 1, 1},
      {0, 0, 0, 0, 1, 1, 1, 1},
      {0, 0, 0, 0, 0, 1, 1, 1},
      {0, 0, 0, 0, 0, 0, 1, 1},
      {0, 0, 0, 0, 0, 0, 0, 1}
   };
   private static Map UT = new HashMap();
   private static Map E = new HashMap();
   private static Map sY = new HashMap();
   private static Map ys = new HashMap();
   private static Map ld = new HashMap();
   private static final Map gp = new HashMap();
   private static final Map oT = new HashMap();
   private static final String fI = ckx.pC(new byte[]{28, 63, 63, 45, 55, 39, 51, 33, 53, 108, 119, 39, 108, 99, 99, 96, 105, 103, 99, 114, 97}, 2, 57);

   public static boolean pC(IDGlobalContext var0) {
      return Boolean.TRUE.equals(var0.getData("INLINING_GLOBALLY_DISABLED"));
   }

   public static BasicBlock pC(BasicBlock var0, long var1) {
      return pC(var0, var1, null);
   }

   public static BasicBlock pC(BasicBlock var0, long var1, Integer var3) {
      BasicBlock var4 = new BasicBlock();

      for (IDInstruction var6 : var0) {
         IDInstruction var7 = var6.duplicate();
         var7.setOffset(var1);
         if (var3 != null) {
            var7.setSize(var3);
         }

         var1 += var7.getSize();
         var4.add(var7);
      }

      return var4;
   }

   public static boolean pC(BasicBlock var0, BasicBlock var1) {
      boolean var2 = false;
      IDMethodContext var3 = ((IDInstruction)var0.get(0)).getContext();
      CFG var4 = var3.getCfg();
      IDTryData var5 = var3.getExceptionData();
      if (var5.unprotectBlock((int)var1.getBase())) {
         var4.deleteIrregularOutEdges(var1);
         var2 = true;
      }

      if (var5.copyProtectedBlock((int)var0.getBase(), (int)var1.getBase())) {
         for (BasicBlock var7 : var0.getIrregularOutputs()) {
            var4.addIrregularEdge(var1, var7, -1);
         }

         var2 = true;
      }

      return var2;
   }

   public static boolean pC(BasicBlock var0, boolean var1) {
      IDMethodContext var2 = ((IDInstruction)var0.get(0)).getContext();
      if (!var2.getExceptionData().unprotectBlock((int)var0.getBase())) {
         return false;
      } else {
         var2.getCfg().deleteIrregularOutEdges(var0);
         if (var1) {
            DUtil.removeUnreachableBlocks(var2);
         }

         return true;
      }
   }

   public static boolean pC(BasicBlock var0, int var1, int var2) {
      return pC(var0, var1, var2, false);
   }

   public static boolean pC(BasicBlock var0, int var1, int var2, boolean var3) {
      if (var1 < 0 || var1 >= var0.size()) {
         throw new IllegalArgumentException();
      } else if (var2 < 0 || var2 >= var0.size()) {
         throw new IllegalArgumentException();
      } else if (var1 == var2) {
         return false;
      } else if (var3 && var2 == var0.size() - 1 && ((IDInstruction)var0.getLast()).getBreakingFlow().isBroken()) {
         throw new IllegalArgumentException();
      } else {
         ArrayList var4 = new ArrayList(var0.size());

         for (IDInstruction var6 : var0) {
            var4.add(new Couple(var6.getOffset(), var6.getSize()));
         }

         IDInstruction var10 = (IDInstruction)var0.remove(var1);
         var0.add(var2, var10);
         int var11 = 0;

         for (IDInstruction var8 : var0) {
            Couple var9 = (Couple)var4.get(var11);
            var8.setOffset((Long)var9.getFirst());
            var8.setSize((Integer)var9.getSecond());
            var11++;
         }

         return true;
      }
   }

   public static IDExpression pC(IDMethodContext var0, IDCallInfo var1) {
      return pC(var0, var1, 30, 1, false);
   }

   public static IDExpression pC(IDMethodContext var0, IDCallInfo var1, int var2, int var3, boolean var4) {
      for (IDExpression var6 : var1.getArguments()) {
         if (var6.hasSideEffects(var0, true)) {
            return null;
         }
      }

      IDexUnit var27 = var0.getDex();
      IDexMethod var28 = pC(var1, var27);
      if (var28 == null) {
         return null;
      } else if (bqh.pC(var28, var0.getGlobalContext()).pC()) {
         return null;
      } else {
         String var7 = var28.getSignature(false);
         Map var8;
         if (var4) {
            var8 = null;
         } else {
            var8 = ((bpr)var0.getGlobalContext()).UT;
            Object var9 = var8.get(var7);
            if (var9 != null) {
               if (var9 != A && var9 != kS) {
                  return ((IDExpression)var9).duplicate();
               }

               return null;
            }
         }

         if (var8 != null) {
            var8.put(var7, kS);
         }

         IDExpression var29 = null;

         try {
            if (var28.getData().isConstructor()) {
               return null;
            } else if ("V".equals(var28.getReturnType().getSignature())) {
               return null;
            } else if (var28.getData().getCodeItem().getControlFlowGraph().getInstructionCount() >= var2) {
               return null;
            } else if (!Boolean.FALSE.equals(((com.pnfsoftware.jeb.corei.parsers.dex.vi)var27).Er().pC(var28.getIndex()))) {
               return null;
            } else {
               bpx var10 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var0.getGlobalContext().getDecompiler()).pC(var28);
               var10.A(false);
               var10.pC(var0.isParseExceptions());
               var10.pC(var0.getWatchdog());
               var10.pC(var0.getDecompilationFlags());
               var10.pC(var0);

               try {
                  var10.A();
               } catch (Exception var24) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var24, var28.getSignature(false));
                  return null;
               }

               label532: {
                  IDMasterOptimizer var11 = var0.getGlobalContext().createMasterOptimizer(var10);
                  var11.setSafeMode(true);
                  var11.setPolicyForOptimizerTag("inliner", false);
                  var11.setPolicyForOptimizerTag("slow", false);
                  var11.perform();
                  CFG var34 = var10.getCfg();
                  if (var34.getInstructionCount() == 1) {
                     IDInstruction var13 = (IDInstruction)var34.get(0).get(0);
                     if (var13.isReturn()) {
                        var29 = var13.getReturnExpression();
                        if (var29 != null) {
                           if (!var29.hasSideEffects(var10, true)) {
                              var27.getContextInfoProvider().setMethodCAT(var7, ContextAccessType.NONE_SAFE);
                           }
                           break label532;
                        }
                     }
                  }

                  List var36 = var1.getArguments();

                  for (IDExpression var15 : var36) {
                     if (!(var15 instanceof IDImm)) {
                        var29 = null;
                        return null;
                     }
                  }

                  List var37 = var10.getParameterVariables();
                  if (var36.size() != var37.size()) {
                     var29 = null;
                     return null;
                  }

                  DUtil.insertHeaderBlock(var10, var36.size(), 1);
                  var34 = var10.getCfg();
                  int var41 = 0;

                  for (IDExpression var17 : var36) {
                     IDInstruction var18 = var10.createAssign((IDExpression)var37.get(var41), var17).withOffset(var41);
                     var34.get(0).set(var41, var18);
                     var41++;
                  }

                  var11.perform();
                  if (var34.getInstructionCount() != 1) {
                     return null;
                  }

                  IDInstruction var46 = (IDInstruction)var34.get(0).get(0);
                  if (!var46.isReturn()) {
                     return null;
                  }

                  var29 = var46.getReturnExpression();
                  if (var29 == null || !(var29 instanceof IDImm)) {
                     return null;
                  }

                  if (var8 != null) {
                     var8.remove(var7);
                  }

                  pC(var0.getGlobalContext(), var28);
                  return var29;
               }

               if (var29 instanceof IDImm) {
                  if (var8 != null) {
                     var8.put(var7, (IDImm)var29);
                  }

                  pC(var0.getGlobalContext(), var28);
                  return var29;
               } else if (var3 == 0 || !pC(var29, null, var3)) {
                  var29 = null;
                  return null;
               } else {
                  List var38 = var10.getParameterVariables();

                  for (IDVar var47 : DUtil.collectVars(var29)) {
                     int var49 = var38.indexOf(var47);
                     if (var49 < 0 || var49 >= var1.getCountOfArguments()) {
                        var29 = null;
                        return null;
                     }

                     IDExpression var50 = var1.getArgument(var49);
                     if (var29.equals(var47)) {
                        var29 = var50.duplicate();
                        break;
                     }

                     if (var29.replaceVariable(var47, var50) < 0) {
                        var29 = null;
                        return null;
                     }
                  }

                  if (var8 != null) {
                     var8.remove(var7);
                  }

                  pC(var0.getGlobalContext(), var28);
                  return var29;
               }
            }
         } catch (Exception var25) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var25, var7 + " from " + var0.getMethodSignature());
            return null;
         } finally {
            if (var8 != null && var29 == null) {
               var8.put(var7, A);
            }
         }
      }
   }

   public static void pC(IDMethodContext var0, String var1) {
      Object var2 = (Map)var0.getData("INLINED_METHOD_CALLS");
      if (var2 == null) {
         var2 = new HashMap();
         var0.setData("INLINED_METHOD_CALLS", var2);
      }

      var2.compute(var1, (var0x, var1x) -> var1x == null ? 1 : var1x + 1);
   }

   public static int A(IDMethodContext var0, String var1) {
      Map var2 = (Map)var0.getData("INLINED_METHOD_CALLS");
      return var2 == null ? 0 : (Integer)var2.getOrDefault(var1, 0);
   }

   public static boolean pC(IDGlobalContext var0, IDexItem var1) {
      return pC(var0, var1, "Inlined contents");
   }

   public static boolean A(IDGlobalContext var0, IDexItem var1) {
      return pC(var0, var1, null);
   }

   public static boolean pC(IDGlobalContext var0, IDexItem var1, String var2) {
      return var1 == null ? false : com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var0.getDecompiler(), var1, var2);
   }

   public static boolean pC(IDExpression var0, Collection var1, int var2) {
      if (var0 instanceof IDImm) {
         return true;
      } else if (!(var0 instanceof IDVar)) {
         if (var0 instanceof IDOperation && var2 > 0) {
            for (IDExpression var4 : var0.getSubExpressions()) {
               if (!pC(var4, var1, var2 - 1)) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return var1 == null || var1.contains((IDVar)var0);
      }
   }

   public static boolean pC(IDExpression var0) {
      if (var0 instanceof IDImm) {
         return true;
      } else if (var0 instanceof IDOperation) {
         for (IDExpression var2 : var0.getSubExpressions()) {
            if (!pC(var2)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean pC(CFG var0, Map var1, Collection var2, IDInstruction[] var3) {
      long var4 = -1L;
      int var6 = 0;

      for (Integer var8 : var2) {
         Collection var9 = (Collection)var1.get(var8);
         if (var9 == null || var9.size() != 1) {
            return false;
         }

         long var10 = (Long)var9.iterator().next();
         if (var6 == 0) {
            var4 = var10;
         } else if (var4 != var10) {
            return false;
         }

         var6++;
      }

      var3[0] = (IDInstruction)var0.getInstruction(var4);
      return true;
   }

   public static boolean pC(CFG var0, Map var1, Collection var2, IDImm[] var3) {
      HashSet var4 = null;
      IDImm var5 = null;

      for (Integer var7 : var2) {
         Collection var8 = (Collection)var1.get(var7);
         if (var8 == null) {
            return false;
         }

         if (var4 == null) {
            for (long var10 : var8) {
               if (var10 < 0L) {
                  return false;
               }

               IDInstruction var12 = (IDInstruction)var0.getInstruction(var10);
               if (var12.getOpcode() != DOpcodeType.IR_ASSIGN || !(var12.getOperand2() instanceof IDImm)) {
                  return false;
               }

               IDImm var13 = (IDImm)var12.getOperand2();
               if (var5 == null) {
                  var5 = var13;
               } else if (!var5.equalsEx(var13, false)) {
                  return false;
               }
            }

            var4 = new HashSet(var8);
         } else if (!CollectionUtil.compare(var8, var4, false)) {
            return false;
         }
      }

      var3[0] = var5;
      return true;
   }

   public static IDexMethod pC(IDCallInfo var0, IDexUnit var1) {
      String var2 = var0.getMethodSignature();
      IDexMethod var3 = var1.getMethod(var2);
      if (var3 == null) {
         return null;
      } else {
         Boolean var4 = null;
         DInvokeType var5 = var0.getInvokeType();
         switch (var5) {
            case VIRTUAL:
            case INTERFACE:
            case SUPER:
               var4 = true;
               break;
            case STATIC:
            case NEW:
               var4 = false;
            case DIRECT:
               break;
            default:
               return null;
         }

         if (var4 != null) {
            bhd var6 = ((com.pnfsoftware.jeb.corei.parsers.dex.vi)var1).hK();
            bhd.Av var7 = var6.pC(var4, var3, true, true, false);
            if (!var7.pC() || var7.A().size() != 1) {
               return null;
            }

            int var8 = (Integer)var7.A().iterator().next();
            var3 = var1.getMethod(var8);
         }

         return var3 != null && var3.isInternal() && var3.getData().getCodeItem() != null ? var3 : null;
      }
   }

   public static boolean pC(IDMasterOptimizer var0, Class var1, boolean var2) {
      IDOptimizer var3 = var0.findOptimizer(var1);
      if (!(var3 instanceof AbstractDOptimizer)) {
         return false;
      } else {
         ((AbstractDOptimizer)var3).setEnabled(var2);
         return true;
      }
   }

   public static void pC(BasicBlock var0, long var1, long var3) {
      int var5 = (int)(var3 - var1);
      if (var5 < var0.size()) {
         throw new IllegalArgumentException();
      } else {
         long var6 = var1;
         int var8 = var0.size() - 1;

         for (int var9 = 0; var9 < var0.size(); var9++) {
            IDInstruction var10 = (IDInstruction)var0.get(var9);
            var10.setOffset(var6);
            if (var9 != var8) {
               var10.setSize(1);
            } else {
               var10.setSize(var5 - var9);
            }

            var6++;
         }
      }
   }

   private static int pC(int var0) {
      switch (var0) {
         case 66:
            return 1;
         case 67:
            return 3;
         case 68:
            return 7;
         case 69:
         case 71:
         case 72:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         default:
            throw new RuntimeException("Illegal source type");
         case 70:
            return 6;
         case 73:
            return 4;
         case 74:
            return 5;
         case 83:
            return 2;
         case 90:
            return 0;
      }
   }

   public static boolean pC(int var0, int var1) {
      int var2 = pC(var0);
      int var3 = pC(var1);
      return wS[var2][var3] == 1;
   }

   public static IDImm pC(IDGlobalContext var0, IDImm var1, int var2, int var3) {
      if (!pC(var2, var3)) {
         throw new IllegalArgumentException();
      } else if (var2 == var3) {
         return var1;
      } else {
         long var4 = var1.getRawValue();
         switch (var2) {
            case 66:
               switch (var3) {
                  case 68:
                     return var0.createDouble((byte)var4);
                  case 70:
                     return var0.createFloat((byte)var4);
                  case 73:
                     return var0.createInt((byte)var4);
                  case 74:
                     return var0.createLong((byte)var4);
                  case 83:
                     return var0.createShort((byte)var4);
                  default:
                     throw new RuntimeException();
               }
            case 67:
               switch (var3) {
                  case 68:
                     return var0.createDouble((char)var4);
                  case 69:
                  case 71:
                  case 72:
                  default:
                     throw new RuntimeException();
                  case 70:
                     return var0.createFloat((char)var4);
                  case 73:
                     return var0.createInt((char)var4);
                  case 74:
                     return var0.createLong((char)var4);
               }
            case 68:
            case 69:
            case 71:
            case 72:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            default:
               throw new RuntimeException();
            case 70:
               switch (var3) {
                  case 68:
                     return var0.createDouble(Float.intBitsToFloat((int)var4));
                  default:
                     throw new RuntimeException();
               }
            case 73:
               switch (var3) {
                  case 68:
                     return var0.createDouble((int)var4);
                  case 70:
                     return var0.createFloat((int)var4);
                  case 74:
                     return var0.createLong((int)var4);
                  default:
                     throw new RuntimeException();
               }
            case 74:
               switch (var3) {
                  case 68:
                     return var0.createDouble(var4);
                  case 70:
                     return var0.createFloat((float)var4);
                  default:
                     throw new RuntimeException();
               }
            case 83:
               switch (var3) {
                  case 68:
                     return var0.createDouble((short)var4);
                  case 69:
                  case 71:
                  case 72:
                  default:
                     throw new RuntimeException();
                  case 70:
                     return var0.createFloat((short)var4);
                  case 73:
                     return var0.createInt((short)var4);
                  case 74:
                     return var0.createLong((short)var4);
               }
         }
      }
   }

   public static boolean pC(String var0) {
      return E.containsKey(var0);
   }

   public static char A(String var0) {
      return (Character)sY.getOrDefault(var0, '\u0000');
   }

   public static boolean pC(IDMethodContext var0) {
      return pC(var0, true);
   }

   public static boolean pC(IDMethodContext var0, boolean var1) {
      boolean var2 = true;
      CFG var3 = var0.getCfg();
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      var3.getGraphRepresentation(var4, var5);
      bou var6 = new bou(var4, var5);
      ArrayList var7;
      if (var2) {
         var7 = new ArrayList(var3.size());

         for (int var8 = 0; var8 < var3.size(); var8++) {
            int var9 = var8 + 1;
            var7.add(new bou.Sv(var9));
            BasicBlock var10 = var3.get(var8);
            if (((IDInstruction)var10.getLast()).isSwitch()) {
               for (BasicBlock var12 : var10.getOutputs()) {
                  bru var13 = new bru(var3, var12.getBase(), var10.getBase());
                  var13.pC(true);
                  var13.A(true);
                  if (var13.kS() && !var13.pC().contains(var10.getBase())) {
                     ((bou.Sv)var7.get(var8)).pC(var3.indexOf(var12) + 1);
                  }
               }
            }
         }
      } else {
         var7 = null;
      }

      Map var14 = var6.pC(var6.A(var7));
      int[] var15 = new int[var14.size()];

      for (int var18 : new TreeMap(var14).keySet()) {
         var15[var18 - 1] = (Integer)var14.get(var18) - 1;
      }

      brb var17 = new brb(var0);
      if (var17.reorder(var15) == null) {
         return false;
      } else {
         if (var1) {
            bxf var19 = new bxf();
            var19.assignLocalFields(var0);
            var19.A();
            caq var20 = new caq();
            var20.assignLocalFields(var0);
            var20.perform();
         }

         return true;
      }
   }

   public static int pC(IDMethodContext var0, BasicBlock var1, BasicBlock var2, boolean var3) {
      IDTryData var4 = var0.getExceptionData();
      if (var4.compareHandlers((int)var1.getBase(), (int)var2.getBase())) {
         return 0;
      } else if (var3) {
         return -1;
      } else if (var1.canThrow()) {
         return var2.canThrow() ? -1 : 2;
      } else {
         return var2.canThrow() ? 3 : 1;
      }
   }

   public static boolean pC(BasicBlock var0) {
      if (var0.size() >= 2
         && ((IDInstruction)var0.get(0)).isStoreException()
         && ((IDInstruction)var0.getLast()).isThrow()
         && ((IDInstruction)var0.getLast()).getThrowExpression() == ((IDInstruction)var0.get(0)).getStoredExceptionVariable()) {
         for (int var1 = 1; var1 < var0.size() - 1; var1++) {
            if (!((IDInstruction)var0.get(var1)).isNop()) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static Set pC(CFG var0, BasicBlock var1) {
      Assert.a(var1.irrinsize() > 0, "The header block is not a handler!");
      ArrayDeque var2 = new ArrayDeque();
      var2.add(0L);
      HashSet var3 = new HashSet();

      while (!var2.isEmpty()) {
         long var4 = (Long)var2.remove();
         if (var3.add(var4)) {
            BasicBlock var6 = var0.getBlockAt(var4);

            for (BasicBlock var8 : var6.getOutputs()) {
               var2.add(var8.getBase());
            }

            for (BasicBlock var11 : var6.getIrregularOutputs()) {
               if (var11 != var1) {
                  var2.add(var11.getBase());
               }
            }
         }
      }

      HashSet var9 = new HashSet();
      var0.getBlocksView().forEach(var1x -> var9.add(var1x.getBase()));
      var9.removeAll(var3);
      return var9;
   }

   public static String pC(IDMethodContext var0, int var1) {
      if (var1 < 0) {
         return var1 != -1 ? null : "Ljava/lang/Throwable;";
      } else {
         IDexType var2 = var0.getDex().getType(var1);
         return var2 == null ? null : var2.getSignature(false);
      }
   }

   public static Map pC(CFG var0, boolean var1) {
      IdentityHashMap var2 = new IdentityHashMap();
      BasicBlock var3 = var0.get(0);

      for (BasicBlock var5 : var0) {
         if (var5 == var3) {
            var2.put(var3, Sets.newHashSet(var3));
         } else {
            var2.put(var5, new LinkedHashSet(var0.getBlocks()));
         }
      }

      boolean var12;
      do {
         var12 = false;

         for (BasicBlock var6 : var0) {
            if (var6 != var3) {
               LinkedHashSet var7 = new LinkedHashSet();
               int var8 = 0;

               for (BasicBlock var11 : var1 ? var6.getAllInputs() : var6.getInputs()) {
                  if (var8 == 0) {
                     var7.addAll((Collection)var2.get(var11));
                  } else {
                     var7.retainAll((Collection)var2.get(var11));
                  }

                  var8++;
               }

               var7.add(var6);
               if (var12) {
                  var2.put(var6, var7);
               } else if (!var7.equals(var2.get(var6))) {
                  var2.put(var6, var7);
                  var12 = true;
               }
            }
         }
      } while (var12);

      return var2;
   }

   public static boolean pC(CFG var0, BasicBlock var1, BasicBlock var2) {
      if (var0 == null || var1 == null || var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1 == var2) {
         return true;
      } else if (var1.getBase() == 0L) {
         return true;
      } else if (var2.insize() == 1 && var2.irrinsize() == 0 && var2.getInputBlock(0) == var1) {
         return true;
      } else {
         Map var3 = pC(var0, true);
         return ((Set)var3.get(var2)).contains(var1);
      }
   }

   public static BasicBlock pC(CFG var0, Collection var1) {
      Map var2 = pC(var0, true);
      Set var3 = null;

      for (BasicBlock var5 : var1) {
         Set var6 = (Set)var2.get(var5);
         if (var6 == null || var6.isEmpty()) {
            return null;
         }

         if (var3 == null) {
            var3 = var6;
         } else if (var3.retainAll(var6) && var3.isEmpty()) {
            return null;
         }
      }

      return var3 != null && !var3.isEmpty() ? (BasicBlock)Lists.last(new ArrayList(var3)) : null;
   }

   public static boolean pC(IDMethodContext var0, BasicBlock var1, String var2) {
      if (var1.irroutsize() == 0) {
         return false;
      } else {
         List var3 = var0.getExceptionData().getBlockHandlers((int)var1.getBase());

         for (int var4 = var3.size() - 1; var4 >= 0; var4--) {
            IDExceptionHandler var5 = (IDExceptionHandler)var3.get(var4);
            int var6 = var5.getTypeIndex();
            if (var6 == -1) {
               return true;
            }

            String var7 = pC(var0, var6);
            if (var7 != null && var0.getTypeInfoProvider().isCompatible(var2, var7)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean pC(IDMethodContext var0, BasicBlock var1, BasicBlock var2) {
      if (var1.irroutsize() == 0) {
         return false;
      } else {
         for (IDExceptionHandler var5 : var0.getExceptionData().getBlockHandlers((int)var1.getBase())) {
            if (var5.isCatchAll(var0)) {
               if (var5.getAddress() != (int)var2.getBase()) {
                  return false;
               }

               return true;
            }
         }

         return false;
      }
   }

   public static boolean A(BasicBlock var0, boolean var1) {
      if (!((IDInstruction)var0.getLast()).isJump()) {
         return false;
      } else {
         int var2 = var0.size() - 1;
         if (var2 > 0) {
            int var3 = 0;
            if (var1 && ((IDInstruction)var0.get(0)).isStoreException()) {
               var3 = 1;
            }

            while (var3 < var2) {
               if (!((IDInstruction)var0.get(var3)).isNop()) {
                  return false;
               }

               var3++;
            }
         }

         return true;
      }
   }

   public static boolean pC(IDOptimizer var0, int var1) {
      if (var1 < 0) {
         return false;
      } else {
         if (!var0.getTags().contains("deobfuscator")) {
            pC.debug("Setting a %s on an IR optimizer not tagged as %s is meaningless!", "DEOBFUSCATOR_SCORE_MULTIPLIER", "deobfuscator");
         }

         var0.setData("DEOBFUSCATOR_SCORE_MULTIPLIER", var1);
         return true;
      }
   }

   public static int pC(IDOptimizer var0) {
      if (!var0.getTags().contains("deobfuscator")) {
         return 0;
      } else {
         Object var1 = var0.getData("DEOBFUSCATOR_SCORE_MULTIPLIER");
         return !(var1 instanceof Integer) ? 1 : (Integer)var1;
      }
   }

   public static void A(IDOptimizer var0) {
      pC(var0, 1);
   }

   public static void kS(IDOptimizer var0) {
      pC(var0, 2);
   }

   public static void wS(IDOptimizer var0) {
      pC(var0, 5);
   }

   public static void UT(IDOptimizer var0) {
      pC(var0, 10);
   }

   public static int A(IDExpression var0) {
      int var1 = var0.getType().getSlotCount();
      if (var1 == 1) {
         return 32;
      } else {
         return var1 == 2 ? 64 : var1 * 32;
      }
   }

   public static boolean kS(IDExpression var0) {
      if (!var0.getType().isBoolean()) {
         return false;
      } else if (var0 instanceof IDImm var3) {
         return (var3.getRawValue() & 1L) == 1L;
      } else {
         if (var0 instanceof IDOperation var1) {
            JavaOperatorType var2 = var1.getOperatorType();
            if (var2 == JavaOperatorType.LOG_IDENT) {
               return kS(var1.getRight());
            }

            if (var2 == JavaOperatorType.LOG_OR) {
               return kS(var1.getLeft());
            }
         }

         return false;
      }
   }

   public static boolean wS(IDExpression var0) {
      if (!var0.getType().isBoolean()) {
         return false;
      } else if (var0 instanceof IDImm var3) {
         return (var3.getRawValue() & 1L) == 0L;
      } else {
         if (var0 instanceof IDOperation var1) {
            JavaOperatorType var2 = var1.getOperatorType();
            if (var2 == JavaOperatorType.LOG_IDENT) {
               return wS(var1.getRight());
            }

            if (var2 == JavaOperatorType.LOG_AND) {
               return wS(var1.getLeft());
            }
         }

         return false;
      }
   }

   public static IDExpression pC(IDExpression var0, IDGlobalContext var1) {
      if (var0 instanceof IDImm var4) {
         boolean var3 = (var4.getRawValue() & 1L) == 1L;
         return var1.createBoolean(!var3);
      } else if (var0 instanceof IDOperation var2 && var2.canReverse()) {
         var2.reverse();
         return var2;
      } else {
         return var1.createPredicate(JavaOperatorType.LOG_NOT, null, var0);
      }
   }

   public static boolean UT(IDExpression var0) {
      if (var0 instanceof IDStaticField var1) {
         String var2 = var1.getClassSignature();
         if (gp.containsKey(var2) && var1.getType().getSignature().equals(var2)) {
            return true;
         }
      }

      if (var0 instanceof IDCallInfo var4) {
         String var5 = var4.getMethodSignature();
         if (oT.containsKey(var5)) {
            IDExpression var3 = var4.getArgument(0);
            if (var3 instanceof IDImm) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean E(IDExpression var0) {
      if (var0 instanceof IDImm) {
         return true;
      } else if (var0 instanceof IDReferenceType) {
         return true;
      } else if (var0.isCastOperation()) {
         IDExpression var4 = var0.asOperation().getOperand2();
         return E(var4);
      } else if (var0 instanceof IDNewArrayInfo var1) {
         for (IDExpression var3 : var1.getInitialValues()) {
            if (!E(var3)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static Map A(IDGlobalContext var0) {
      return (Map)var0.getData(fI, var0x -> new ConcurrentHashMap());
   }

   public static void pC(IDGlobalContext var0, String var1) {
      A(var0).compute(var1, (var0x, var1x) -> var1x == null ? 1 : var1x + 1);
      pC(var0, var0.getDex().getMethod(var1), S.L("Potential decryptor"));
   }

   public static boolean A(IDGlobalContext var0, String var1) {
      return (Integer)A(var0).getOrDefault(var1, 0) > 0;
   }

   static {
      UT.put("boolean", "Ljava/lang/Boolean;");
      UT.put("byte", "Ljava/lang/Byte;");
      UT.put("char", "Ljava/lang/Character;");
      UT.put("short", "Ljava/lang/Short;");
      UT.put("int", "Ljava/lang/Integer;");
      UT.put("long", "Ljava/lang/Long;");
      UT.put("float", "Ljava/lang/Float;");
      UT.put("double", "Ljava/lang/Double;");
      E.put("Ljava/lang/Boolean;", "boolean");
      E.put("Ljava/lang/Byte;", "byte");
      E.put("Ljava/lang/Character;", "char");
      E.put("Ljava/lang/Short;", "short");
      E.put("Ljava/lang/Integer;", "int");
      E.put("Ljava/lang/Long;", "long");
      E.put("Ljava/lang/Float;", "float");
      E.put("Ljava/lang/Double;", "double");
      sY.put("Ljava/lang/Boolean;", 'Z');
      sY.put("Ljava/lang/Byte;", 'B');
      sY.put("Ljava/lang/Character;", 'C');
      sY.put("Ljava/lang/Short;", 'S');
      sY.put("Ljava/lang/Integer;", 'I');
      sY.put("Ljava/lang/Long;", 'J');
      sY.put("Ljava/lang/Float;", 'F');
      sY.put("Ljava/lang/Double;", 'D');
      ys.put("boolean", "Z");
      ys.put("byte", "B");
      ys.put("char", "C");
      ys.put("short", "S");
      ys.put("int", "I");
      ys.put("long", "J");
      ys.put("float", "F");
      ys.put("double", "D");
      ld.put("Z", "boolean");
      ld.put("B", "byte");
      ld.put("C", "char");
      ld.put("S", "short");
      ld.put("I", "int");
      ld.put("J", "long");
      ld.put("F", "float");
      ld.put("D", "double");
      gp.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 70, 79, 93, 92, 88, 93, 23}, 2, 16), "Z");
      gp.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 80, 84, 84, 2}, 2, 113), "B");
      gp.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 32, 65, 65, 67, 88, 90, 71, 73, 82, 9}, 2, 31), "C");
      gp.put(ckx.pC(new byte[]{126, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 124, 59, 7, 29, 6, 79}, 1, 50), "S");
      gp.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 42, 71, 84, 84, 94, 92, 65, 23}, 2, 25), "I");
      gp.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 47, 70, 78, 86, 2}, 2, 108), "J");
      gp.put(ckx.pC(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 37, 69, 79, 80, 77, 2}, 2, 77), "F");
      gp.put(ckx.pC(new byte[]{127, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 107, 43, 26, 23, 14, 9, 94}, 1, 51), "D");
      oT.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               33,
               70,
               79,
               93,
               92,
               88,
               93,
               23,
               13,
               12,
               70,
               83,
               89,
               89,
               69,
               0,
               20,
               73,
               57,
               69,
               41,
               74,
               0,
               24,
               5,
               0,
               3,
               19,
               78,
               14,
               91,
               49,
               79,
               14,
               10,
               3,
               8,
               2,
               82
            },
            2,
            77
         ),
         "Z"
      );
      oT.put(
         ckx.pC(
            new byte[]{
               48,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               109,
               59,
               13,
               17,
               94,
               22,
               19,
               72,
               23,
               13,
               25,
               16,
               42,
               41,
               78,
               106,
               107,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               109,
               59,
               13,
               17,
               94
            },
            1,
            124
         ),
         "B"
      );
      oT.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               32,
               65,
               65,
               67,
               88,
               90,
               71,
               73,
               82,
               9,
               29,
               12,
               67,
               77,
               76,
               58,
               23,
               46,
               5,
               68,
               38,
               9,
               45,
               4,
               5,
               89,
               14,
               93,
               76,
               8,
               26,
               20,
               15,
               34,
               14,
               7,
               27,
               13,
               10,
               21,
               17,
               23,
               72
            },
            2,
            215
         ),
         "C"
      );
      oT.put(
         ckx.pC(
            new byte[]{
               107,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               59,
               7,
               29,
               6,
               79,
               22,
               19,
               72,
               23,
               13,
               25,
               16,
               42,
               41,
               78,
               123,
               122,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               124,
               59,
               7,
               29,
               6,
               79
            },
            1,
            39
         ),
         "S"
      );
      oT.put(
         ckx.pC(
            new byte[]{
               -83,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               102,
               39,
               26,
               17,
               2,
               2,
               23,
               73,
               22,
               19,
               72,
               23,
               13,
               25,
               16,
               42,
               41,
               78,
               97,
               96,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               102,
               39,
               26,
               17,
               2,
               2,
               23,
               73
            },
            1,
            225
         ),
         "I"
      );
      oT.put(
         ckx.pC(
            new byte[]{
               -104,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               99,
               35,
               1,
               9,
               92,
               22,
               19,
               72,
               23,
               13,
               25,
               16,
               42,
               41,
               78,
               98,
               99,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               99,
               35,
               1,
               9,
               92
            },
            1,
            212
         ),
         "J"
      );
      oT.put(
         ckx.pC(
            new byte[]{
               26,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               105,
               42,
               3,
               14,
               21,
               79,
               22,
               19,
               72,
               23,
               13,
               25,
               16,
               42,
               41,
               78,
               110,
               111,
               101,
               38,
               11,
               23,
               23,
               78,
               67,
               13,
               15,
               9,
               72,
               105,
               42,
               3,
               14,
               21,
               79
            },
            1,
            86
         ),
         "F"
      );
      oT.put(
         ckx.pC(
            new byte[]{
               15,
               5,
               17,
               15,
               19,
               70,
               11,
               9,
               26,
               71,
               7,
               39,
               70,
               85,
               83,
               85,
               92,
               8,
               1,
               30,
               68,
               81,
               94,
               64,
               73,
               111,
               41,
               90,
               37,
               74,
               32,
               15,
               65,
               23,
               15,
               75,
               67,
               14,
               28,
               71,
               70,
               48,
               28,
               85,
               3,
               10,
               3,
               82
            },
            2,
            205
         ),
         "D"
      );
   }
}
