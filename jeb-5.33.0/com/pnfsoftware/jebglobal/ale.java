package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.MemoryAccessInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ale {
   private static final ILogger pC = GlobalLog.getLogger(ale.class);
   private ajk A;
   private Integer kS;
   private Integer wS;
   private int UT = 3;
   private int E = 0;
   private static final Set sY = new HashSet();

   static boolean pC(IEGeneric var0, int[] var1) {
      if (var0 == null) {
         return false;
      } else if (var0.isVar() && var0.asVar().isStackReference()) {
         int var7 = var0.asVar().getAddress().intValue();
         var1[0] = var7;
         return true;
      } else {
         if (var0.isOperation()) {
            IEOperation var2 = var0.asOperation();
            if (var2.getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB)) {
               IEGeneric var3 = var2.getOperand1();
               IEGeneric var4 = var2.getOperand2();
               if (var4.isImm() && var3.isVar() && var3.asVar().isStackReference()) {
                  int var5 = var3.asVar().getAddress().intValue();
                  int var6 = (int)var4.asImm().getValueAsAddress();
                  var5 = var2.getOperationType() == OperationType.ADD ? var5 + var6 : var5 - var6;
                  var1[0] = var5;
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static boolean pC(IEGeneric var0) {
      if (var0 == null) {
         return false;
      } else if (var0.isVar() && var0.asVar().hasFlags(64)) {
         return true;
      } else {
         if (var0.isOperation()) {
            IEOperation var1 = var0.asOperation();
            if (var1.getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB)) {
               IEGeneric var2 = var1.getOperand1();
               IEGeneric var3 = var1.getOperand2();
               IEGeneric var4 = null;
               if (var2.isVar() && var2.asVar().hasFlags(64)) {
                  var4 = var3;
               } else if (var3.isVar() && var3.asVar().hasFlags(64)) {
                  var4 = var2;
               }

               if (var4 != null) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public ale(ajk var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   public MemoryAccessInfo pC() {
      if (!this.A.isStaticCallsite()) {
         return null;
      } else {
         String var1 = this.A.getStaticCallsite().getName();
         if (var1 != null && !var1.isEmpty()) {
            if (var1.startsWith("→")) {
               var1 = var1.substring(1);
            }

            if (var1.startsWith("__builtin_")) {
               var1 = var1.substring(10);
            }

            int var2 = var1.indexOf(".dll!");
            if (var2 >= 0) {
               var1 = var1.substring(var2 + 5);
            }

            com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.Av var3 = com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.rQ.pC().pC(var1);
            if (var3 != null && !var3.kS()) {
               try {
                  return this.pC(var3);
               } catch (Exception var11) {
                  acj.pC(var11);
                  return null;
               }
            } else {
               if (this.pC(var1)) {
                  this.kS = 0;
                  this.wS = 0;
                  this.UT = 0;
               } else {
                  if (!var1.equals("memset") && !var1.equals("__builtin_memset")) {
                     return null;
                  }

                  byte var4 = 3;
                  byte var5 = 0;
                  byte var6 = 2;
                  byte var7 = 1;
                  this.UT = 2;
                  if (this.A.getPrototype() == null || this.A.getPrototype().getParameterTypes().size() != var4 || this.A.getCountOfArguments() != var4) {
                     return null;
                  }

                  IEGeneric var8 = this.A.getArgument(var6);
                  this.wS = this.kS(var8);
                  if (this.wS == null) {
                     return null;
                  }

                  this.wS = this.wS * var7;
                  IEGeneric var9 = this.A.getArgument(var5);
                  this.kS = this.A(var9);
                  if (this.kS == null) {
                     return null;
                  }
               }

               IERoutineContext var12 = this.A.getContext();
               MemoryAccessInfo var13 = new MemoryAccessInfo();
               IdRanges var14 = new IdRanges();
               var14.addAll(var12.getStackVariables(this.kS, this.kS + this.wS));
               IdRanges var15 = (this.UT & 1) != 0 ? var14 : new IdRanges();
               IdRanges var16 = (this.UT & 2) != 0 ? var14 : new IdRanges();
               var13.setStackAccessRanges(var15, var16);
               IdRanges var17 = (this.E & 1) != 0 ? null : new IdRanges();
               IdRanges var10 = (this.E & 2) != 0 ? null : new IdRanges();
               var13.setGlobalsAccessRanges(var17, var10);
               return var13;
            }
         } else {
            return null;
         }
      }
   }

   private Integer A(IEGeneric var1) {
      if (var1 == null) {
         return null;
      } else if (var1.isVar() && var1.asVar().isStackReference()) {
         int var8 = var1.asVar().getAddress().intValue();
         return var8;
      } else {
         if (var1.isOperation()) {
            IEOperation var2 = var1.asOperation();
            if (var2.getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB)) {
               IEGeneric var3 = var2.getOperand1();
               IEGeneric var4 = var2.getOperand2();
               if (var4.isImm() && var3.isVar() && var3.asVar().isStackReference()) {
                  int var5 = var3.asVar().getAddress().intValue();
                  int var6 = (int)var4.asImm().getValueAsAddress();
                  int var7 = var2.getOperationType() == OperationType.ADD ? var5 + var6 : var5 - var6;
                  return var7;
               }
            }
         }

         return null;
      }
   }

   private Integer kS(IEGeneric var1) {
      return var1 instanceof IEImm && var1.asImm().canReadAsUnsignedLong() ? EUtil.evalAsSaturatedPositiveInt(var1.asImm()) : null;
   }

   private boolean pC(String var1) {
      int var2 = var1.indexOf("__aeabi_");
      if (var2 < 0) {
         return false;
      } else {
         String var3 = var1.substring(var2 + "__aeabi_".length());
         return sY.contains(var3);
      }
   }

   private MemoryAccessInfo pC(com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.Av var1) {
      if (this.A.getPrototype() == null) {
         return null;
      } else {
         MemoryAccessInfo var2 = null;
         HashSet var3 = new HashSet();

         for (int var5 : var1.A().keySet()) {
            com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.cq var6 = var1.pC(var5);
            com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.bO var7 = var6.A();
            if (var7 != null) {
               var3.add(var5);
               if ((var7.pC() & 3) == 0) {
                  return null;
               }

               if (var5 - 1 >= this.A.getCountOfArguments()) {
                  return null;
               }

               IEGeneric var8 = this.A.getArgument(var5 - 1);
               if (!(var8 instanceof IEImm) && !this.A.getHintArgumentPointsToExternalMemory(var5 - 1)) {
                  Integer var9 = this.A(var8);
                  if (var9 == null) {
                     return null;
                  }

                  int var10 = 0;
                  com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.bO.Av var11 = var7.A();
                  if (var11.pC == 0) {
                     var10 = var11.A;
                  } else if (var11.pC == 1) {
                     int var12 = var11.A - 1;
                     IEGeneric var13 = this.A.getArgument(var12);
                     var10 = this.wS(var13);
                  } else if (var11.pC == 2) {
                     int var23 = var11.A - 1;
                     var10 = this.pC(var11.kS, var23);
                  }

                  if (var10 == 0) {
                     return null;
                  }

                  int var24 = 0;
                  var11 = var7.kS();
                  if (var11.pC == 0) {
                     var24 = var11.A;
                  } else if (var11.pC == 1) {
                     int var25 = var11.A - 1;
                     IEGeneric var14 = this.A.getArgument(var25);
                     var24 = this.wS(var14);
                  } else if (var11.pC == 2) {
                     int var26 = var11.A - 1;
                     var24 = this.pC(var11.kS, var26);
                  }

                  if (var24 == 0) {
                     return null;
                  }

                  int var27 = var10 * var24;
                  IERoutineContext var28 = this.A.getContext();
                  Collection var15 = var28.getStackVariables(var9, var9 + var27);
                  if (!var15.isEmpty() && var2 == null) {
                     var2 = new MemoryAccessInfo(false);
                  }

                  if ((var7.pC() & 1) != 0) {
                     for (IEVar var17 : var15) {
                        var2.getStackReads().add(var17);
                     }
                  }

                  if ((var7.pC() & 2) != 0) {
                     for (IEVar var30 : var15) {
                        var2.getStackWrites().add(var30);
                     }
                  }
               }
            }
         }

         int var18 = 0;

         for (IWildcardType var20 : this.A.getPrototype().getParameterTypes()) {
            if (var20.isPointer() && !var3.contains(++var18)) {
               if (var18 - 1 >= this.A.getCountOfArguments()) {
                  return null;
               }

               IEGeneric var21 = this.A.getArgument(var18 - 1);
               if (!(var21 instanceof IEImm) && !this.A.getHintArgumentPointsToExternalMemory(var18 - 1)) {
                  return null;
               }
            }
         }

         if (var2 == null) {
            var2 = new MemoryAccessInfo(false);
         }

         return var2;
      }
   }

   private int wS(IEGeneric var1) {
      Integer var2 = this.kS(var1);
      return var2 == null ? 0 : var2;
   }

   private int pC(int var1, int var2) {
      switch (var1) {
         case 1:
            INativeType var21 = this.A.getPrototype().getParameterType(var2).getNativeType();
            if (var21 != null) {
               INativeType var26 = TypeUtil.getNonAlias(var21);
               if (var26 instanceof IReferenceType) {
                  INativeType var27 = ((IReferenceType)var26).getPointedType();
                  return var27.getSize();
               }
            }

            return 0;
         case 2:
            IEGeneric var20 = this.A.getArgument(var2);
            int var25 = this.UT(var20);
            return var25 < 0 ? 0 : var25;
         case 3:
            IEGeneric var19 = this.A.getArgument(var2);
            int var24 = this.UT(var19);
            return var24 < 0 ? 0 : var24 + 1;
         case 4:
            ITypeManager var18 = this.A.getContext().getWildcardTypeManager().getNativeTypeManager();
            return var18.getPrimitives().getType("int").getSize();
         case 5:
            ITypeManager var17 = this.A.getContext().getWildcardTypeManager().getNativeTypeManager();
            return var17.getPrimitives().getType("long").getSize();
         case 6:
            ITypeManager var16 = this.A.getContext().getWildcardTypeManager().getNativeTypeManager();
            return var16.getPrimitives().getType("long long").getSize();
         case 7:
            IEGeneric var15 = this.A.getArgument(var2);
            Integer var23 = this.A(var15);
            if (var23 != null) {
               IEVar var5 = this.A.getContext().getStackVariable(var23);
               if (var5 != null) {
                  CFG var6 = this.A.getContext().getCfg();
                  Couple var7 = var6.getInstructionLocation(this.A);
                  if (var7 != null) {
                     BasicBlock var8 = (BasicBlock)var7.getFirst();

                     for (int var9 = (Integer)var7.getSecond() - 1; var9 >= 0; var9--) {
                        IEStatement var10 = (IEStatement)var8.get(var9);
                        if (var10.isAssignTo(var5) && var10.asAssign().getSrcOperand() instanceof IEImm) {
                           IEImm var11 = var10.asAssign().getSrcOperand().asImm();
                           if (var11.canReadAsLong()) {
                              long var12 = var11.getValueAsLong();
                              return (int)var12;
                           }
                        }
                     }
                  }
               }
            }

            return 0;
         case 8:
            IEGeneric var14 = this.A.getArgument(var2);
            int var22 = this.E(var14);
            return var22 < 0 ? 0 : var22;
         case 9:
            IEGeneric var3 = this.A.getArgument(var2);
            int var4 = this.E(var3);
            return var4 < 0 ? 0 : var4 + 1;
         default:
            return 0;
      }
   }

   private int UT(IEGeneric var1) {
      if (var1 instanceof IEImm && ((IEImm)var1).isStringLiteral()) {
         String var2 = var1.asImm().getStringLiteral();
         return var2.length();
      } else {
         return -1;
      }
   }

   private int E(IEGeneric var1) {
      if (var1 instanceof IEImm && ((IEImm)var1).isStringLiteral()) {
         String var2 = var1.asImm().getStringLiteral();
         return var2.length();
      } else {
         return -1;
      }
   }

   static {
      sY.add("dadd");
      sY.add("ddiv");
      sY.add("dmul");
      sY.add("drsub");
      sY.add("dsub");
      sY.add("cdcmpeq");
      sY.add("cdcmple");
      sY.add("cdrcmple");
      sY.add("dcmpeq");
      sY.add("dcmplt");
      sY.add("dcmple");
      sY.add("dcmpge");
      sY.add("dcmpgt");
      sY.add("dcmpun");
      sY.add("fadd");
      sY.add("fdiv");
      sY.add("fmul");
      sY.add("frsub");
      sY.add("fsub");
      sY.add("cfcmpeq");
      sY.add("cfcmple");
      sY.add("cfrcmple");
      sY.add("fcmpeq");
      sY.add("fcmplt");
      sY.add("fcmple");
      sY.add("fcmpge");
      sY.add("fcmpgt");
      sY.add("fcmpun");
      sY.add("d2iz");
      sY.add("d2uiz");
      sY.add("d2lz");
      sY.add("d2ulz");
      sY.add("f2iz");
      sY.add("f2uiz");
      sY.add("f2lz");
      sY.add("f2ulz");
      sY.add("d2f");
      sY.add("f2d");
      sY.add("h2f");
      sY.add("h2f_alt");
      sY.add("f2h");
      sY.add("f2h_alt");
      sY.add("d2h");
      sY.add("d2h_alt");
      sY.add("i2d");
      sY.add("ui2d");
      sY.add("l2d");
      sY.add("ul2d");
      sY.add("i2f");
      sY.add("ui2f");
      sY.add("l2f");
      sY.add("ul2f");
      sY.add("lmul");
      sY.add("ldivmod");
      sY.add("uldivmod");
      sY.add("llsl");
      sY.add("llsr");
      sY.add("lasr");
      sY.add("lcmp");
      sY.add("ulcmp");
      sY.add("idiv");
      sY.add("uidiv");
      sY.add("idivmod");
      sY.add("uidivmod");
      sY.add("idiv0");
      sY.add("ldiv0");
   }
}
