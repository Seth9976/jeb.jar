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

public class anj {
   private static final ILogger xK = GlobalLog.getLogger(anj.class);
   static final int q = 1;
   static final int RF = 2;
   private aln Dw;
   private Integer Uv;
   private Integer oW;
   private int gO = 3;
   private int nf = 0;
   private static final Set gP = new HashSet();

   static boolean q(IEGeneric var0, int[] var1) {
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

   public static boolean q(IEGeneric var0) {
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

   public anj(aln var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   public MemoryAccessInfo q() {
      if (!this.Dw.isStaticCallsite()) {
         return null;
      } else {
         String var1 = this.Dw.getStaticCallsite().getName();
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

            com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.CU var3 = com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.EE.q().q(var1);
            if (var3 != null && !var3.Uv()) {
               try {
                  return this.q(var3);
               } catch (Exception var11) {
                  aeb.q(var11);
                  return null;
               }
            } else {
               if (this.q(var1)) {
                  this.Uv = 0;
                  this.oW = 0;
                  this.gO = 0;
               } else {
                  if (!var1.equals("memset") && !var1.equals("__builtin_memset")) {
                     return null;
                  }

                  byte var4 = 3;
                  byte var5 = 0;
                  byte var6 = 2;
                  byte var7 = 1;
                  this.gO = 2;
                  if (this.Dw.getPrototype() == null || this.Dw.getPrototype().getParameterTypes().size() != var4 || this.Dw.getCountOfArguments() != var4) {
                     return null;
                  }

                  IEGeneric var8 = this.Dw.getArgument(var6);
                  this.oW = this.xK(var8);
                  if (this.oW == null) {
                     return null;
                  }

                  this.oW = this.oW * var7;
                  IEGeneric var9 = this.Dw.getArgument(var5);
                  this.Uv = this.RF(var9);
                  if (this.Uv == null) {
                     return null;
                  }
               }

               IERoutineContext var12 = this.Dw.getContext();
               MemoryAccessInfo var13 = new MemoryAccessInfo();
               IdRanges var14 = new IdRanges();
               var14.addAll(var12.getStackVariables(this.Uv, this.Uv + this.oW));
               IdRanges var15 = (this.gO & 1) != 0 ? var14 : new IdRanges();
               IdRanges var16 = (this.gO & 2) != 0 ? var14 : new IdRanges();
               var13.setStackAccessRanges(var15, var16);
               IdRanges var17 = (this.nf & 1) != 0 ? null : new IdRanges();
               IdRanges var10 = (this.nf & 2) != 0 ? null : new IdRanges();
               var13.setGlobalsAccessRanges(var17, var10);
               return var13;
            }
         } else {
            return null;
         }
      }
   }

   private Integer RF(IEGeneric var1) {
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

   private Integer xK(IEGeneric var1) {
      return var1 instanceof IEImm && var1.asImm().canReadAsUnsignedLong() ? EUtil.evalAsSaturatedPositiveInt(var1.asImm()) : null;
   }

   private boolean q(String var1) {
      int var2 = var1.indexOf("__aeabi_");
      if (var2 < 0) {
         return false;
      } else {
         String var3 = var1.substring(var2 + "__aeabi_".length());
         return gP.contains(var3);
      }
   }

   private MemoryAccessInfo q(com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.CU var1) {
      if (this.Dw.getPrototype() == null) {
         return null;
      } else {
         MemoryAccessInfo var2 = null;
         HashSet var3 = new HashSet();

         for (int var5 : var1.Dw().keySet()) {
            com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.iZ var6 = var1.q(var5);
            com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.Nt var7 = var6.xK();
            if (var7 != null) {
               var3.add(var5);
               if ((var7.q() & 3) == 0) {
                  return null;
               }

               if (var5 - 1 >= this.Dw.getCountOfArguments()) {
                  return null;
               }

               IEGeneric var8 = this.Dw.getArgument(var5 - 1);
               if (!(var8 instanceof IEImm) && !this.Dw.getHintArgumentPointsToExternalMemory(var5 - 1)) {
                  Integer var9 = this.RF(var8);
                  if (var9 == null) {
                     return null;
                  }

                  int var10 = 0;
                  com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.Nt.eo var11 = var7.RF();
                  if (var11.q == 0) {
                     var10 = var11.RF;
                  } else if (var11.q == 1) {
                     int var12 = var11.RF - 1;
                     IEGeneric var13 = this.Dw.getArgument(var12);
                     var10 = this.Dw(var13);
                  } else if (var11.q == 2) {
                     int var23 = var11.RF - 1;
                     var10 = this.q(var11.xK, var23);
                  }

                  if (var10 == 0) {
                     return null;
                  }

                  int var24 = 0;
                  var11 = var7.xK();
                  if (var11.q == 0) {
                     var24 = var11.RF;
                  } else if (var11.q == 1) {
                     int var25 = var11.RF - 1;
                     IEGeneric var14 = this.Dw.getArgument(var25);
                     var24 = this.Dw(var14);
                  } else if (var11.q == 2) {
                     int var26 = var11.RF - 1;
                     var24 = this.q(var11.xK, var26);
                  }

                  if (var24 == 0) {
                     return null;
                  }

                  int var27 = var10 * var24;
                  IERoutineContext var28 = this.Dw.getContext();
                  Collection var15 = var28.getStackVariables(var9, var9 + var27);
                  if (!var15.isEmpty() && var2 == null) {
                     var2 = new MemoryAccessInfo(false);
                  }

                  if ((var7.q() & 1) != 0) {
                     for (IEVar var17 : var15) {
                        var2.getStackReads().add(var17);
                     }
                  }

                  if ((var7.q() & 2) != 0) {
                     for (IEVar var30 : var15) {
                        var2.getStackWrites().add(var30);
                     }
                  }
               }
            }
         }

         int var18 = 0;

         for (IWildcardType var20 : this.Dw.getPrototype().getParameterTypes()) {
            if (var20.isPointer() && !var3.contains(++var18)) {
               if (var18 - 1 >= this.Dw.getCountOfArguments()) {
                  return null;
               }

               IEGeneric var21 = this.Dw.getArgument(var18 - 1);
               if (!(var21 instanceof IEImm) && !this.Dw.getHintArgumentPointsToExternalMemory(var18 - 1)) {
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

   private int Dw(IEGeneric var1) {
      Integer var2 = this.xK(var1);
      return var2 == null ? 0 : var2;
   }

   private int q(int var1, int var2) {
      switch (var1) {
         case 1:
            INativeType var21 = this.Dw.getPrototype().getParameterType(var2).getNativeType();
            if (var21 != null) {
               INativeType var26 = TypeUtil.getNonAlias(var21);
               if (var26 instanceof IReferenceType) {
                  INativeType var27 = ((IReferenceType)var26).getPointedType();
                  return var27.getSize();
               }
            }

            return 0;
         case 2:
            IEGeneric var20 = this.Dw.getArgument(var2);
            int var25 = this.Uv(var20);
            return var25 < 0 ? 0 : var25;
         case 3:
            IEGeneric var19 = this.Dw.getArgument(var2);
            int var24 = this.Uv(var19);
            return var24 < 0 ? 0 : var24 + 1;
         case 4:
            ITypeManager var18 = this.Dw.getContext().getWildcardTypeManager().getNativeTypeManager();
            return var18.getPrimitives().getType("int").getSize();
         case 5:
            ITypeManager var17 = this.Dw.getContext().getWildcardTypeManager().getNativeTypeManager();
            return var17.getPrimitives().getType("long").getSize();
         case 6:
            ITypeManager var16 = this.Dw.getContext().getWildcardTypeManager().getNativeTypeManager();
            return var16.getPrimitives().getType("long long").getSize();
         case 7:
            IEGeneric var15 = this.Dw.getArgument(var2);
            Integer var23 = this.RF(var15);
            if (var23 != null) {
               IEVar var5 = this.Dw.getContext().getStackVariable(var23);
               if (var5 != null) {
                  CFG var6 = this.Dw.getContext().getCfg();
                  Couple var7 = var6.getInstructionLocation(this.Dw);
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
            IEGeneric var14 = this.Dw.getArgument(var2);
            int var22 = this.oW(var14);
            return var22 < 0 ? 0 : var22;
         case 9:
            IEGeneric var3 = this.Dw.getArgument(var2);
            int var4 = this.oW(var3);
            return var4 < 0 ? 0 : var4 + 1;
         default:
            return 0;
      }
   }

   private int Uv(IEGeneric var1) {
      if (var1 instanceof IEImm && ((IEImm)var1).isStringLiteral()) {
         String var2 = var1.asImm().getStringLiteral();
         return var2.length();
      } else {
         return -1;
      }
   }

   private int oW(IEGeneric var1) {
      if (var1 instanceof IEImm && ((IEImm)var1).isStringLiteral()) {
         String var2 = var1.asImm().getStringLiteral();
         return var2.length();
      } else {
         return -1;
      }
   }

   static {
      gP.add("dadd");
      gP.add("ddiv");
      gP.add("dmul");
      gP.add("drsub");
      gP.add("dsub");
      gP.add("cdcmpeq");
      gP.add("cdcmple");
      gP.add("cdrcmple");
      gP.add("dcmpeq");
      gP.add("dcmplt");
      gP.add("dcmple");
      gP.add("dcmpge");
      gP.add("dcmpgt");
      gP.add("dcmpun");
      gP.add("fadd");
      gP.add("fdiv");
      gP.add("fmul");
      gP.add("frsub");
      gP.add("fsub");
      gP.add("cfcmpeq");
      gP.add("cfcmple");
      gP.add("cfrcmple");
      gP.add("fcmpeq");
      gP.add("fcmplt");
      gP.add("fcmple");
      gP.add("fcmpge");
      gP.add("fcmpgt");
      gP.add("fcmpun");
      gP.add("d2iz");
      gP.add("d2uiz");
      gP.add("d2lz");
      gP.add("d2ulz");
      gP.add("f2iz");
      gP.add("f2uiz");
      gP.add("f2lz");
      gP.add("f2ulz");
      gP.add("d2f");
      gP.add("f2d");
      gP.add("h2f");
      gP.add("h2f_alt");
      gP.add("f2h");
      gP.add("f2h_alt");
      gP.add("d2h");
      gP.add("d2h_alt");
      gP.add("i2d");
      gP.add("ui2d");
      gP.add("l2d");
      gP.add("ul2d");
      gP.add("i2f");
      gP.add("ui2f");
      gP.add("l2f");
      gP.add("ul2f");
      gP.add("lmul");
      gP.add("ldivmod");
      gP.add("uldivmod");
      gP.add("llsl");
      gP.add("llsr");
      gP.add("lasr");
      gP.add("lcmp");
      gP.add("ulcmp");
      gP.add("idiv");
      gP.add("uidiv");
      gP.add("idivmod");
      gP.add("uidivmod");
      gP.add("idiv0");
      gP.add("ldiv0");
   }
}
