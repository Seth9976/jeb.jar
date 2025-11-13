package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantString;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Bitmap;
import com.pnfsoftware.jeb.util.format.Strings;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class arb extends AbstractEOptimizer {
   private INativeDecompilerContext pC;

   public arb() {
      this(null);
   }

   public arb(INativeDecompilerContext var1) {
      this.pC = var1;
   }

   @Override
   protected int perform() {
      int var1 = 0;
      arb.Av var2 = new arb.Av();

      for (BasicBlock var4 : this.cfg) {
         var2.pC(var4);

         while (true) {
            int var5 = var2.pC();
            if (var5 < 0) {
               break;
            }

            if (var5 != 0) {
               var1 += var2.A();
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private static boolean pC(byte var0) {
      return var0 <= 127 && (var0 >= 32 || var0 == 13 && var0 == 10 && var0 == 9);
   }

   private boolean pC(int var1) {
      return var1 == 8 || var1 == 16 || var1 == 32 || var1 == 64;
   }

   private class Av {
      BasicBlock pC;
      int A;
      int kS;
      Boolean wS;
      Long UT;
      Long E;
      List sY = new ArrayList();
      Bitmap ys = new Bitmap(512);
      byte[] ld = new byte[512];
      ByteBuffer gp = ByteBuffer.wrap(this.ld);

      Av() {
         this.gp.order(arb.this.ectx.getGlobalContext().isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
      }

      void pC(BasicBlock var1) {
         this.pC = var1;
         this.A = 0;
      }

      int pC() {
         if (this.A >= this.pC.size()) {
            return -1;
         } else {
            this.kS = -1;

            int var1;
            for (var1 = this.A; var1 < this.pC.size(); var1++) {
               IEStatement var2 = (IEStatement)this.pC.get(var1);
               if (!(var2 instanceof IEAssign) || !(((IEAssign)var2).getSrcOperand() instanceof IEImm)) {
                  break;
               }

               IEAssign var3 = var2.asAssign();
               IEGeneric var4 = var3.getDstOperand();
               IEImm var5 = var3.getSrcOperand().asImm();
               if (!arb.this.pC(var5.getBitsize())) {
                  break;
               }

               boolean var6;
               long var7;
               if (var4 instanceof IEVar) {
                  IEVar var9 = var4.asVar();
                  if (var9.isStackVariable()) {
                     var6 = false;
                  } else {
                     if (!var9.isGlobalVariable()) {
                        break;
                     }

                     var6 = true;
                  }

                  var7 = var9.getAddress();
               } else {
                  if (!(var4 instanceof IEMem)) {
                     break;
                  }

                  IEGeneric var15 = ((IEMem)var4).getReference();
                  if (var15 instanceof IEVar) {
                     IEVar var10 = var15.asVar();
                     if (var10.isStackReference()) {
                        var6 = false;
                     } else {
                        if (!var10.isGlobalReference()) {
                           break;
                        }

                        var6 = true;
                     }

                     var7 = var10.getAddress();
                  } else {
                     if (!var15.isOperation(OperationType.ADD, OperationType.SUB)) {
                        break;
                     }

                     IEOperation var17 = var15.asOperation();
                     if (!(var17.getOperand1() instanceof IEVar) || !(var17.getOperand2() instanceof IEImm)) {
                        break;
                     }

                     IEVar var11 = var17.getOperand1().asVar();
                     if (var11.isStackReference()) {
                        var6 = false;
                     } else {
                        if (!var11.isGlobalReference()) {
                           break;
                        }

                        var6 = true;
                     }

                     long var12 = var17.getOperand2().asImm().getValueAsLong();
                     if (var17.getOperationType() == OperationType.SUB) {
                        var12 = -var12;
                     }

                     var7 = var11.getAddress() + var12;
                  }
               }

               if (this.kS < 0) {
                  this.kS = var1;
                  this.wS = var6;
                  this.UT = null;
                  this.E = null;
                  this.sY.clear();
               } else if (this.wS != var6) {
                  break;
               }

               if (this.UT == null) {
                  this.UT = var7;
               } else if (this.pC(var7, this.UT) < 0) {
                  if (this.E != null && Long.compareUnsigned(this.E - var7, 512L) > 0) {
                     break;
                  }

                  this.UT = var7;
               }

               long var16 = var7 + var5.getBitsize() / 8;
               if (this.E == null) {
                  this.E = var16;
               } else if (this.pC(var16, this.E) > 0) {
                  if (this.UT != null && Long.compareUnsigned(var16 - this.UT, 512L) > 0) {
                     break;
                  }

                  this.E = var16;
               }

               this.sY.add(new Couple(var7, var5));
            }

            this.A = var1 > this.A ? var1 : this.A + 1;
            if (this.kS < 0) {
               return 0;
            } else if (this.sY.size() >= 1 && Long.compareUnsigned(this.UT, this.E) < 0) {
               long var14 = this.E - this.UT;
               return Long.compareUnsigned(var14, 4L) >= 0 && Long.compareUnsigned(var14, 512L) <= 0 ? 1 : 0;
            } else {
               return 0;
            }
         }
      }

      private int pC(long var1, long var3) {
         return this.wS ? Long.compareUnsigned(var1, var3) : Long.compare(var1, var3);
      }

      int A() {
         int var1 = (int)(this.E - this.UT);
         this.ys.clear();

         for (Couple var3 : this.sY) {
            int var4 = (int)((Long)var3.getFirst() - this.UT);
            int var5 = ((IEImm)var3.getSecond()).getBitsize() / 8;
            this.ys.setRange(var4, var4 + var5);
         }

         Arrays.fill(this.ld, (byte)0);
         this.gp.position(0);
         this.gp.limit(var1);

         for (Couple var15 : this.sY) {
            int var17 = (int)((Long)var15.getFirst() - this.UT);
            IEImm var19 = (IEImm)var15.getSecond();
            int var6 = var19.getBitsize() / 8;
            switch (var6) {
               case 1:
                  this.gp.put(var17, (byte)var19.getValueAsLong());
                  break;
               case 2:
                  this.gp.putShort(var17, (short)var19.getValueAsLong());
                  break;
               case 3:
               case 5:
               case 6:
               case 7:
               default:
                  return 0;
               case 4:
                  this.gp.putInt(var17, (int)var19.getValueAsLong());
                  break;
               case 8:
                  this.gp.putLong(var17, var19.getValueAsLong());
            }
         }

         int var14 = 0;
         int var16 = -1;

         for (int var18 = 0; var18 < var1; var18++) {
            if (this.ys.get(var18)) {
               if (this.ld[var18] == 0) {
                  if (var16 != -1) {
                     int var20 = var18 - var16;
                     if (var20 >= 4) {
                        long var21 = this.UT + var16;
                        long var8 = this.UT + var18;
                        int[] var10 = new int[2];
                        if (this.pC(var21, var8, var10)) {
                           long var11 = this.UT + var16;
                           if (this.pC(var11, var16, var18, this.kS + var10[0], this.kS + var10[1])) {
                              var14++;
                           }
                        }
                     }

                     var16 = -1;
                  }
               } else if (arb.pC(this.ld[var18])) {
                  if (var16 == -1) {
                     var16 = var18;
                  }
               } else {
                  var16 = -1;
               }
            } else {
               var16 = -1;
            }
         }

         return var14;
      }

      private boolean pC(long var1, long var3, int[] var5) {
         int var6 = -1;
         int var7 = -1;
         int var8 = 0;

         for (Couple var10 : this.sY) {
            long var11 = (Long)var10.getFirst();
            if (var11 >= var1 && var11 <= var3) {
               if (var6 < 0 || var8 < var6) {
                  var6 = var8;
               }

               if (var7 < 0 || var8 > var7) {
                  var7 = var8;
               }

               long var13 = var11 + ((IEImm)var10.getSecond()).getBitsize() / 8;
               if (var3 < var13 && var13 - 1L != var3) {
                  return false;
               }
            }

            var8++;
         }

         if (var6 >= 0 && var7 >= 0) {
            for (int var15 = var7 + 1; var15 < this.sY.size(); var15++) {
               Couple var16 = (Couple)this.sY.get(var15);
               long var17 = (Long)var16.getFirst();
               if (var17 >= var1 && var17 <= var3) {
                  return false;
               }
            }

            var5[0] = var6;
            var5[1] = var7;
            return true;
         } else {
            return false;
         }
      }

      private boolean pC(long var1, int var3, int var4, int var5, int var6) {
         int var7 = var4 - var3;
         String var8 = Strings.decodeASCII(this.ld, var3, var7);
         Object[] var10000 = new Object[]{var8};
         ICConstantFactory var9;
         if (arb.this.pC != null) {
            var9 = arb.this.pC.getHighLevelContext().getConstantFactory();
         } else {
            if (arb.this.ectx.getConverter().getDecompiler() == null) {
               return false;
            }

            var9 = arb.this.ectx.getConverter().getDecompiler().getHighLevelContext().getConstantFactory();
         }

         ICConstantString var10 = var9.createString(var8, -1L);
         IEVar var11;
         if (this.wS) {
            var11 = arb.this.ectx.getGlobalContext().createGlobalReference(null, var1);
         } else {
            var11 = arb.this.ectx.createStackReference(var1, arb.this.ectx.getWildcardTypeManager().create("char*"));
         }

         IEImm var12 = arb.this.ectx.createImm(var1, var11.getBitsize()).duplicateToMutable();
         var12.setCustomAST(var10);
         IECall var13 = arb.this.ectx.createBuiltinMethodCall("strcpy", null, var11, var12);
         LinkedHashSet var14 = new LinkedHashSet();

         for (int var15 = var5; var15 < var6; var15++) {
            IEStatement var16 = (IEStatement)this.pC.get(var15);
            var14.addAll(var16.getLowerLevelAddresses());
            IENop var17 = arb.this.ectx.createNop(var16);
            this.pC.set(var15, var17);
         }

         var13.setSize(((IEStatement)this.pC.get(var6)).getSize());
         var14.addAll(((IEStatement)this.pC.get(var6)).getLowerLevelAddresses());
         var13.setLowerLevelAddresses(var14);
         this.pC.set(var6, var13);
         return true;
      }
   }
}
