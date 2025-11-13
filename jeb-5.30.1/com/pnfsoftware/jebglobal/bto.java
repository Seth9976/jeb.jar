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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class bto {
   private static final ILogger q = GlobalLog.getLogger(bto.class);
   private static final Object RF = new Object();
   private static final Object xK = new Object();
   private static final String Dw = "INLINED_METHOD_CALLS";
   private static final int[][] Uv = new int[][]{
      {1, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 1, 0, 1, 1, 1, 1},
      {0, 0, 1, 0, 1, 1, 1, 1},
      {0, 0, 0, 1, 1, 1, 1, 1},
      {0, 0, 0, 0, 1, 1, 1, 1},
      {0, 0, 0, 0, 0, 1, 1, 1},
      {0, 0, 0, 0, 0, 0, 1, 1},
      {0, 0, 0, 0, 0, 0, 0, 1}
   };
   private static Map oW = new HashMap();
   private static Map gO = new HashMap();
   private static Map nf = new HashMap();
   private static Map gP = new HashMap();
   private static Map za = new HashMap();
   private static final String lm = "DEOBFUSCATOR_SCORE_MULTIPLIER";
   private static final Map zz = new HashMap();
   private static final Map JY = new HashMap();
   private static final String HF = cvm.q(new byte[]{51, 15, 31, 27, 17, 11, 26, 29, 8, 13, 19, 27, 1, 6, 17, 11, 9, 4, 27, 29, 1}, 1, 108);

   public static void q(IDGlobalContext var0, boolean var1) {
      var0.setData("INLINING_GLOBALLY_DISABLED", var1);
   }

   public static boolean q(IDGlobalContext var0) {
      return Boolean.TRUE.equals(var0.getData("INLINING_GLOBALLY_DISABLED"));
   }

   public static BasicBlock q(BasicBlock var0, long var1) {
      return q(var0, var1, null);
   }

   public static BasicBlock q(BasicBlock var0, long var1, Integer var3) {
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

   public static boolean q(BasicBlock var0, BasicBlock var1) {
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

   public static boolean q(BasicBlock var0, boolean var1) {
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

   public static boolean q(BasicBlock var0, int var1, int var2) {
      return q(var0, var1, var2, false);
   }

   public static boolean q(BasicBlock var0, int var1, int var2, boolean var3) {
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

   public static IDExpression q(IDMethodContext var0, IDCallInfo var1) {
      return q(var0, var1, 30, 1, false);
   }

   public static IDExpression q(IDMethodContext var0, IDCallInfo var1, int var2, int var3, boolean var4) {
      for (IDExpression var6 : var1.getArguments()) {
         if (var6.hasSideEffects(var0, true)) {
            return null;
         }
      }

      IDexUnit var27 = var0.getDex();
      IDexMethod var28 = q(var1, var27);
      if (var28 == null) {
         return null;
      } else if (bun.q(var28, var0.getGlobalContext()).q()) {
         return null;
      } else {
         String var7 = var28.getSignature(false);
         Map var8;
         if (var4) {
            var8 = null;
         } else {
            var8 = ((btx)var0.getGlobalContext()).Uv;
            Object var9 = var8.get(var7);
            if (var9 != null) {
               if (var9 != RF && var9 != xK) {
                  return ((IDExpression)var9).duplicate();
               }

               return null;
            }
         }

         if (var8 != null) {
            var8.put(var7, xK);
         }

         IDExpression var29 = null;

         try {
            if (var28.getData().isConstructor()) {
               return null;
            } else if ("V".equals(var28.getReturnType().getSignature())) {
               return null;
            } else if (var28.getData().getCodeItem().getControlFlowGraph().getInstructionCount() >= var2) {
               return null;
            } else if (!Boolean.FALSE.equals(((com.pnfsoftware.jeb.corei.parsers.dex.bK)var27).Rr().xK(var28.getIndex()))) {
               return null;
            } else {
               bud var10 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var0.getGlobalContext().getDecompiler()).q(var28);
               var10.RF(false);
               var10.q(var0.isParseExceptions());
               var10.q(var0.getWatchdog());
               var10.q(var0.getDecompilationFlags());
               var10.q(var0);

               try {
                  var10.Dw();
               } catch (Exception var24) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var24, var28.getSignature(false));
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

                  q(var0.getGlobalContext(), var28);
                  return var29;
               }

               if (var29 instanceof IDImm) {
                  if (var8 != null) {
                     var8.put(var7, (IDImm)var29);
                  }

                  q(var0.getGlobalContext(), var28);
                  return var29;
               } else if (var3 == 0 || !q(var29, null, var3)) {
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

                  q(var0.getGlobalContext(), var28);
                  return var29;
               }
            }
         } catch (Exception var25) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var25, var7 + " from " + var0.getMethodSignature());
            return null;
         } finally {
            if (var8 != null && var29 == null) {
               var8.put(var7, RF);
            }
         }
      }
   }

   public static void q(IDMethodContext var0, String var1) {
      Object var2 = (Map)var0.getData("INLINED_METHOD_CALLS");
      if (var2 == null) {
         var2 = new HashMap();
         var0.setData("INLINED_METHOD_CALLS", var2);
      }

      var2.compute(var1, (var0x, var1x) -> var1x == null ? 1 : var1x + 1);
   }

   public static int RF(IDMethodContext var0, String var1) {
      Map var2 = (Map)var0.getData("INLINED_METHOD_CALLS");
      return var2 == null ? 0 : (Integer)var2.getOrDefault(var1, 0);
   }

   public static boolean q(IDGlobalContext var0, IDexItem var1) {
      return q(var0, var1, "Inlined contents");
   }

   public static boolean RF(IDGlobalContext var0, IDexItem var1) {
      return q(var0, var1, null);
   }

   public static boolean q(IDGlobalContext var0, IDexItem var1, String var2) {
      return var1 == null ? false : com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var0.getDecompiler(), var1, var2);
   }

   public static boolean q(IDExpression var0, Collection var1, int var2) {
      if (var0 instanceof IDImm) {
         return true;
      } else if (!(var0 instanceof IDVar)) {
         if (var0 instanceof IDOperation && var2 > 0) {
            for (IDExpression var4 : var0.getSubExpressions()) {
               if (!q(var4, var1, var2 - 1)) {
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

   public static boolean q(IDExpression var0) {
      if (var0 instanceof IDImm) {
         return true;
      } else if (var0 instanceof IDOperation) {
         for (IDExpression var2 : var0.getSubExpressions()) {
            if (!q(var2)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean q(CFG var0, Map var1, Collection var2, IDInstruction[] var3) {
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

   public static boolean q(CFG var0, Map var1, Collection var2, IDImm[] var3) {
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

   public static IDexMethod q(IDCallInfo var0, IDexUnit var1) {
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
            bkz var6 = ((com.pnfsoftware.jeb.corei.parsers.dex.bK)var1).TX();
            bkz.eo var7 = var6.q(var4, var3, true, true, false);
            if (!var7.q() || var7.RF().size() != 1) {
               return null;
            }

            int var8 = (Integer)var7.RF().iterator().next();
            var3 = var1.getMethod(var8);
         }

         return var3 != null && var3.isInternal() && var3.getData().getCodeItem() != null ? var3 : null;
      }
   }

   public static void q(CFG var0) {
      for (IDInstruction var2 : var0.instructions()) {
         var2.visitInstruction(new btp());
      }
   }

   public static boolean q(IDMasterOptimizer var0, Class var1, boolean var2) {
      IDOptimizer var3 = var0.findOptimizer(var1);
      if (!(var3 instanceof AbstractDOptimizer)) {
         return false;
      } else {
         ((AbstractDOptimizer)var3).setEnabled(var2);
         return true;
      }
   }

   public static void q(BasicBlock var0, long var1, long var3) {
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

   private static int q(int var0) {
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

   public static boolean q(int var0, int var1) {
      int var2 = q(var0);
      int var3 = q(var1);
      return Uv[var2][var3] == 1;
   }

   public static IDImm q(IDGlobalContext var0, IDImm var1, int var2, int var3) {
      if (!q(var2, var3)) {
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

   public static boolean q(String var0) {
      return gO.containsKey(var0);
   }

   public static char RF(String var0) {
      return (Character)nf.getOrDefault(var0, '\u0000');
   }

   public static boolean q(IDMethodContext var0) {
      return q(var0, true);
   }

   public static boolean q(IDMethodContext var0, boolean var1) {
      boolean var2 = true;
      CFG var3 = var0.getCfg();
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      var3.getGraphRepresentation(var4, var5);
      bsv var6 = new bsv(var4, var5);
      ArrayList var7;
      if (var2) {
         var7 = new ArrayList(var3.size());

         for (int var8 = 0; var8 < var3.size(); var8++) {
            int var9 = var8 + 1;
            var7.add(new bsv.CU(var9));
            BasicBlock var10 = var3.get(var8);
            if (((IDInstruction)var10.getLast()).isSwitch()) {
               for (BasicBlock var12 : var10.getOutputs()) {
                  bwe var13 = new bwe(var3, var12.getBase(), var10.getBase());
                  var13.q(true);
                  var13.RF(true);
                  if (var13.xK() && !var13.q().contains(var10.getBase())) {
                     ((bsv.CU)var7.get(var8)).q(var3.indexOf(var12) + 1);
                  }
               }
            }
         }
      } else {
         var7 = null;
      }

      Map var14 = var6.q(var6.RF(var7));
      int[] var15 = new int[var14.size()];

      for (int var18 : new TreeMap(var14).keySet()) {
         var15[var18 - 1] = (Integer)var14.get(var18) - 1;
      }

      bvj var17 = new bvj(var0);
      if (var17.reorder(var15) == null) {
         return false;
      } else {
         if (var1) {
            cbu var19 = new cbu();
            var19.assignLocalFields(var0);
            var19.RF();
            cfm var20 = new cfm();
            var20.assignLocalFields(var0);
            var20.perform();
         }

         return true;
      }
   }

   public static int q(IDMethodContext var0, BasicBlock var1, BasicBlock var2, boolean var3) {
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

   public static boolean q(BasicBlock var0) {
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

   public static Set q(CFG var0, BasicBlock var1) {
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

   public static boolean RF(CFG var0, BasicBlock var1) {
      if (var1.irrinsize() == 0) {
         return false;
      } else {
         return var1.insize() == 0 ? true : q(var0, var1).contains(var1.getBase());
      }
   }

   public static String q(IDMethodContext var0, int var1) {
      if (var1 < 0) {
         return var1 != -1 ? null : "Ljava/lang/Throwable;";
      } else {
         IDexType var2 = var0.getDex().getType(var1);
         return var2 == null ? null : var2.getSignature(false);
      }
   }

   public static Map q(CFG var0, boolean var1) {
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

   public static boolean q(CFG var0, BasicBlock var1, BasicBlock var2) {
      if (var0 == null || var1 == null || var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1 == var2) {
         return true;
      } else if (var1.getBase() == 0L) {
         return true;
      } else if (var2.insize() == 1 && var2.irrinsize() == 0 && var2.getInputBlock(0) == var1) {
         return true;
      } else {
         Map var3 = q(var0, true);
         return ((Set)var3.get(var2)).contains(var1);
      }
   }

   public static boolean q(CFG var0, long var1, long var3) {
      return q(var0, var0.getBlockAt(var1), var0.getBlockAt(var3));
   }

   public static BasicBlock q(CFG var0, Collection var1) {
      Map var2 = q(var0, true);
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

   public static boolean q(IDMethodContext var0, BasicBlock var1, String var2) {
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

            String var7 = q(var0, var6);
            if (var7 != null && var0.getTypeInfoProvider().isCompatible(var2, var7)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean q(IDMethodContext var0, BasicBlock var1, BasicBlock var2) {
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

   public static boolean RF(BasicBlock var0, boolean var1) {
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

   public static boolean q(IDOptimizer var0, int var1) {
      if (var1 < 0) {
         return false;
      } else {
         if (!var0.getTags().contains("deobfuscator")) {
            q.debug("Setting a %s on an IR optimizer not tagged as %s is meaningless!", "DEOBFUSCATOR_SCORE_MULTIPLIER", "deobfuscator");
         }

         var0.setData("DEOBFUSCATOR_SCORE_MULTIPLIER", var1);
         return true;
      }
   }

   public static int q(IDOptimizer var0) {
      if (!var0.getTags().contains("deobfuscator")) {
         return 0;
      } else {
         Object var1 = var0.getData("DEOBFUSCATOR_SCORE_MULTIPLIER");
         return !(var1 instanceof Integer) ? 1 : (Integer)var1;
      }
   }

   public static void RF(IDOptimizer var0) {
      q(var0, 0);
   }

   public static void xK(IDOptimizer var0) {
      q(var0, 1);
   }

   public static void Dw(IDOptimizer var0) {
      q(var0, 2);
   }

   public static void Uv(IDOptimizer var0) {
      q(var0, 5);
   }

   public static void oW(IDOptimizer var0) {
      q(var0, 10);
   }

   public static int RF(IDExpression var0) {
      int var1 = var0.getType().getSlotCount();
      if (var1 == 1) {
         return 32;
      } else {
         return var1 == 2 ? 64 : var1 * 32;
      }
   }

   public static boolean xK(IDExpression var0) {
      if (!var0.getType().isBoolean()) {
         return false;
      } else if (var0 instanceof IDImm var3) {
         return (var3.getRawValue() & 1L) == 1L;
      } else {
         if (var0 instanceof IDOperation var1) {
            JavaOperatorType var2 = var1.getOperatorType();
            if (var2 == JavaOperatorType.LOG_IDENT) {
               return xK(var1.getRight());
            }

            if (var2 == JavaOperatorType.LOG_OR) {
               return xK(var1.getLeft());
            }
         }

         return false;
      }
   }

   public static boolean Dw(IDExpression var0) {
      if (!var0.getType().isBoolean()) {
         return false;
      } else if (var0 instanceof IDImm var3) {
         return (var3.getRawValue() & 1L) == 0L;
      } else {
         if (var0 instanceof IDOperation var1) {
            JavaOperatorType var2 = var1.getOperatorType();
            if (var2 == JavaOperatorType.LOG_IDENT) {
               return Dw(var1.getRight());
            }

            if (var2 == JavaOperatorType.LOG_AND) {
               return Dw(var1.getLeft());
            }
         }

         return false;
      }
   }

   public static IDExpression q(IDExpression var0, IDGlobalContext var1) {
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

   public static Set Uv(IDExpression var0) {
      HashSet var1 = new HashSet();
      var0.visitDepthPost(new btq(var1));
      return var1;
   }

   public static boolean oW(IDExpression var0) {
      if (var0 instanceof IDStaticField var1) {
         String var2 = var1.getClassSignature();
         if (zz.containsKey(var2) && var1.getType().getSignature().equals(var2)) {
            return true;
         }
      }

      if (var0 instanceof IDCallInfo var4) {
         String var5 = var4.getMethodSignature();
         if (JY.containsKey(var5)) {
            IDExpression var3 = var4.getArgument(0);
            if (var3 instanceof IDImm) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean gO(IDExpression var0) {
      if (var0 instanceof IDImm) {
         return true;
      } else if (var0 instanceof IDReferenceType) {
         return true;
      } else if (var0.isCastOperation()) {
         IDExpression var4 = var0.asOperation().getOperand2();
         return gO(var4);
      } else if (var0 instanceof IDNewArrayInfo var1) {
         for (IDExpression var3 : var1.getInitialValues()) {
            if (!gO(var3)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static Map Dw(IDGlobalContext var0) {
      return (Map)var0.getData(HF, var0x -> new ConcurrentHashMap());
   }

   public static Map RF(IDGlobalContext var0) {
      return Collections.unmodifiableMap(Dw(var0));
   }

   public static void q(IDGlobalContext var0, String var1) {
      Dw(var0).compute(var1, (var0x, var1x) -> var1x == null ? 1 : var1x + 1);
      q(var0, var0.getDex().getMethod(var1), S.L("Potential decryptor"));
   }

   public static boolean RF(IDGlobalContext var0, String var1) {
      return (Integer)Dw(var0).getOrDefault(var1, 0) > 0;
   }

   public static void xK(IDGlobalContext var0) {
      Dw(var0).clear();
   }

   static {
      oW.put("boolean", "Ljava/lang/Boolean;");
      oW.put("byte", "Ljava/lang/Byte;");
      oW.put("char", "Ljava/lang/Character;");
      oW.put("short", "Ljava/lang/Short;");
      oW.put("int", "Ljava/lang/Integer;");
      oW.put("long", "Ljava/lang/Long;");
      oW.put("float", "Ljava/lang/Float;");
      oW.put("double", "Ljava/lang/Double;");
      gO.put("Ljava/lang/Boolean;", "boolean");
      gO.put("Ljava/lang/Byte;", "byte");
      gO.put("Ljava/lang/Character;", "char");
      gO.put("Ljava/lang/Short;", "short");
      gO.put("Ljava/lang/Integer;", "int");
      gO.put("Ljava/lang/Long;", "long");
      gO.put("Ljava/lang/Float;", "float");
      gO.put("Ljava/lang/Double;", "double");
      nf.put("Ljava/lang/Boolean;", 'Z');
      nf.put("Ljava/lang/Byte;", 'B');
      nf.put("Ljava/lang/Character;", 'C');
      nf.put("Ljava/lang/Short;", 'S');
      nf.put("Ljava/lang/Integer;", 'I');
      nf.put("Ljava/lang/Long;", 'J');
      nf.put("Ljava/lang/Float;", 'F');
      nf.put("Ljava/lang/Double;", 'D');
      gP.put("boolean", "Z");
      gP.put("byte", "B");
      gP.put("char", "C");
      gP.put("short", "S");
      gP.put("int", "I");
      gP.put("long", "J");
      gP.put("float", "F");
      gP.put("double", "D");
      za.put("Z", "boolean");
      za.put("B", "byte");
      za.put("C", "char");
      za.put("S", "short");
      za.put("I", "int");
      za.put("J", "long");
      za.put("F", "float");
      za.put("D", "double");
      zz.put(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 70, 79, 93, 92, 88, 93, 23}, 2, 199), "Z");
      zz.put(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 33, 80, 84, 84, 2}, 2, 226), "B");
      zz.put(cvm.q(new byte[]{39, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 108, 43, 9, 19, 19, 2, 23, 17, 23, 73}, 1, 107), "C");
      zz.put(cvm.q(new byte[]{-104, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 124, 59, 7, 29, 6, 79}, 1, 212), "S");
      zz.put(cvm.q(new byte[]{-15, 38, 11, 23, 23, 78, 67, 13, 15, 9, 72, 102, 39, 26, 17, 2, 2, 23, 73}, 1, 189), "I");
      zz.put(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 47, 70, 78, 86, 2}, 2, 208), "J");
      zz.put(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 37, 69, 79, 80, 77, 2}, 2, 98), "F");
      zz.put(cvm.q(new byte[]{15, 5, 17, 15, 19, 70, 11, 9, 26, 71, 7, 39, 70, 85, 83, 85, 92, 8}, 2, 142), "D");
      JY.put(
         cvm.q(
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
               88,
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
            101
         ),
         "Z"
      );
      JY.put(
         cvm.q(
            new byte[]{
               22,
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
            90
         ),
         "B"
      );
      JY.put(
         cvm.q(
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
               66,
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
            113
         ),
         "C"
      );
      JY.put(
         cvm.q(
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
            225
         ),
         "S"
      );
      JY.put(
         cvm.q(
            new byte[]{
               -107,
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
            217
         ),
         "I"
      );
      JY.put(
         cvm.q(
            new byte[]{
               89,
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
            21
         ),
         "J"
      );
      JY.put(
         cvm.q(
            new byte[]{
               -53,
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
            135
         ),
         "F"
      );
      JY.put(
         cvm.q(
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
               65,
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
            6
         ),
         "D"
      );
   }
}
