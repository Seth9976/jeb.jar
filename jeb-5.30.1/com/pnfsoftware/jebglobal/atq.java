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

public class atq extends AbstractEOptimizer {
   private static final int q = 1;
   private static final int RF = 4;
   private static final int xK = 512;
   private INativeDecompilerContext Dw;

   public atq() {
      this(null);
   }

   public atq(INativeDecompilerContext var1) {
      this.Dw = var1;
   }

   @Override
   protected int perform() {
      int var1 = 0;
      atq.eo var2 = new atq.eo();

      for (BasicBlock var4 : this.cfg) {
         var2.q(var4);

         while (true) {
            int var5 = var2.q();
            if (var5 < 0) {
               break;
            }

            if (var5 != 0) {
               var1 += var2.RF();
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private static boolean q(byte var0) {
      return var0 <= 127 && (var0 >= 32 || var0 == 13 && var0 == 10 && var0 == 9);
   }

   private boolean q(int var1) {
      return var1 == 8 || var1 == 16 || var1 == 32 || var1 == 64;
   }

   private class eo {
      BasicBlock q;
      int RF;
      int xK;
      Boolean Dw;
      Long Uv;
      Long oW;
      List gO = new ArrayList();
      Bitmap nf = new Bitmap(512);
      byte[] gP = new byte[512];
      ByteBuffer za = ByteBuffer.wrap(this.gP);

      eo() {
         this.za.order(atq.this.ectx.getGlobalContext().isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
      }

      void q(BasicBlock var1) {
         this.q = var1;
         this.RF = 0;
      }

      int q() {
         if (this.RF >= this.q.size()) {
            return -1;
         } else {
            this.xK = -1;

            int var1;
            for (var1 = this.RF; var1 < this.q.size(); var1++) {
               IEStatement var2 = (IEStatement)this.q.get(var1);
               if (!(var2 instanceof IEAssign) || !(((IEAssign)var2).getSrcOperand() instanceof IEImm)) {
                  break;
               }

               IEAssign var3 = var2.asAssign();
               IEGeneric var4 = var3.getDstOperand();
               IEImm var5 = var3.getSrcOperand().asImm();
               if (!atq.this.q(var5.getBitsize())) {
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

               if (this.xK < 0) {
                  this.xK = var1;
                  this.Dw = var6;
                  this.Uv = null;
                  this.oW = null;
                  this.gO.clear();
               } else if (this.Dw != var6) {
                  break;
               }

               if (this.Uv == null) {
                  this.Uv = var7;
               } else if (this.q(var7, this.Uv) < 0) {
                  if (this.oW != null && Long.compareUnsigned(this.oW - var7, 512L) > 0) {
                     break;
                  }

                  this.Uv = var7;
               }

               long var16 = var7 + var5.getBitsize() / 8;
               if (this.oW == null) {
                  this.oW = var16;
               } else if (this.q(var16, this.oW) > 0) {
                  if (this.Uv != null && Long.compareUnsigned(var16 - this.Uv, 512L) > 0) {
                     break;
                  }

                  this.oW = var16;
               }

               this.gO.add(new Couple(var7, var5));
            }

            this.RF = var1 > this.RF ? var1 : this.RF + 1;
            if (this.xK < 0) {
               return 0;
            } else if (this.gO.size() >= 1 && Long.compareUnsigned(this.Uv, this.oW) < 0) {
               long var14 = this.oW - this.Uv;
               return Long.compareUnsigned(var14, 4L) >= 0 && Long.compareUnsigned(var14, 512L) <= 0 ? 1 : 0;
            } else {
               return 0;
            }
         }
      }

      private int q(long var1, long var3) {
         return this.Dw ? Long.compareUnsigned(var1, var3) : Long.compare(var1, var3);
      }

      int RF() {
         int var1 = (int)(this.oW - this.Uv);
         this.nf.clear();

         for (Couple var3 : this.gO) {
            int var4 = (int)((Long)var3.getFirst() - this.Uv);
            int var5 = ((IEImm)var3.getSecond()).getBitsize() / 8;
            this.nf.setRange(var4, var4 + var5);
         }

         Arrays.fill(this.gP, (byte)0);
         this.za.position(0);
         this.za.limit(var1);

         for (Couple var15 : this.gO) {
            int var17 = (int)((Long)var15.getFirst() - this.Uv);
            IEImm var19 = (IEImm)var15.getSecond();
            int var6 = var19.getBitsize() / 8;
            switch (var6) {
               case 1:
                  this.za.put(var17, (byte)var19.getValueAsLong());
                  break;
               case 2:
                  this.za.putShort(var17, (short)var19.getValueAsLong());
                  break;
               case 3:
               case 5:
               case 6:
               case 7:
               default:
                  return 0;
               case 4:
                  this.za.putInt(var17, (int)var19.getValueAsLong());
                  break;
               case 8:
                  this.za.putLong(var17, var19.getValueAsLong());
            }
         }

         int var14 = 0;
         int var16 = -1;

         for (int var18 = 0; var18 < var1; var18++) {
            if (this.nf.get(var18)) {
               if (this.gP[var18] == 0) {
                  if (var16 != -1) {
                     int var20 = var18 - var16;
                     if (var20 >= 4) {
                        long var21 = this.Uv + var16;
                        long var8 = this.Uv + var18;
                        int[] var10 = new int[2];
                        if (this.q(var21, var8, var10)) {
                           long var11 = this.Uv + var16;
                           if (this.q(var11, var16, var18, this.xK + var10[0], this.xK + var10[1])) {
                              var14++;
                           }
                        }
                     }

                     var16 = -1;
                  }
               } else if (atq.q(this.gP[var18])) {
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

      private boolean q(long var1, long var3, int[] var5) {
         int var6 = -1;
         int var7 = -1;
         int var8 = 0;

         for (Couple var10 : this.gO) {
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
            for (int var15 = var7 + 1; var15 < this.gO.size(); var15++) {
               Couple var16 = (Couple)this.gO.get(var15);
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

      private boolean q(long var1, int var3, int var4, int var5, int var6) {
         int var7 = var4 - var3;
         String var8 = Strings.decodeASCII(this.gP, var3, var7);
         Object[] var10000 = new Object[]{var8};
         ICConstantFactory var9;
         if (atq.this.Dw != null) {
            var9 = atq.this.Dw.getHighLevelContext().getConstantFactory();
         } else {
            if (atq.this.ectx.getConverter().getDecompiler() == null) {
               return false;
            }

            var9 = atq.this.ectx.getConverter().getDecompiler().getHighLevelContext().getConstantFactory();
         }

         ICConstantString var10 = var9.createString(var8, -1L);
         IEVar var11;
         if (this.Dw) {
            var11 = atq.this.ectx.getGlobalContext().createGlobalReference(null, var1);
         } else {
            var11 = atq.this.ectx.createStackReference(var1, atq.this.ectx.getWildcardTypeManager().create("char*"));
         }

         IEImm var12 = atq.this.ectx.createImm(var1, var11.getBitsize()).duplicateToMutable();
         var12.setCustomAST(var10);
         IECall var13 = atq.this.ectx.createBuiltinMethodCall("strcpy", null, var11, var12);
         LinkedHashSet var14 = new LinkedHashSet();

         for (int var15 = var5; var15 < var6; var15++) {
            IEStatement var16 = (IEStatement)this.q.get(var15);
            var14.addAll(var16.getLowerLevelAddresses());
            IENop var17 = atq.this.ectx.createNop(var16);
            this.q.set(var15, var17);
         }

         var13.setSize(((IEStatement)this.q.get(var6)).getSize());
         var14.addAll(((IEStatement)this.q.get(var6)).getLowerLevelAddresses());
         var13.setLowerLevelAddresses(var14);
         this.q.set(var6, var13);
         return true;
      }
   }
}
