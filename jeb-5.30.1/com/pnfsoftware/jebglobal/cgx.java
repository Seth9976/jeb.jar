package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class cgx extends AbstractDOptimizer {
   boolean q = true;
   boolean RF = true;

   public cgx() {
      this(true, true);
   }

   public cgx(boolean var1, boolean var2) {
      this.addTag("deobfuscator");
      this.q = var1;
      this.RF = var2;
   }

   public void q(boolean var1) {
      this.q = var1;
   }

   public boolean q() {
      return this.q;
   }

   public void RF(boolean var1) {
      this.RF = var1;
   }

   public boolean RF() {
      return this.RF;
   }

   @Override
   public int perform() {
      int var1 = 0;
      long var2 = -1L;
      ArrayList var4 = new ArrayList();

      label40:
      while (true) {
         for (IDInstruction var6 : this.cfg.instructions()) {
            if (var6.getOffset() > var2
               && var6.isAssignToVar()
               && var6.getAssignSource() instanceof IDNewArrayInfo var7
               && var7.getType().isArray()
               && var7.getType().getDimensions() == 1) {
               cgx.nI var10 = new cgx.nI(var6);
               if (var10.RF()) {
                  var4.addAll(var10.q());
                  var2 = var6.getOffset();
                  var1++;
                  continue label40;
               }
            }
         }

         if (var1 > 0 && this.q) {
            bwg var9 = new bwg(this.ctx);
            var9.q(var4);
            var9.q();
         }

         return var1;
      }
   }

   private static class CU {
      IDInstruction q;
      Object RF;
      int xK;
      IDVar Dw;
      int Uv;
      int oW;

      public CU(IDInstruction var1, Object var2, int var3, IDVar var4, int var5, int var6) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.Uv = var5;
         this.oW = var6;
      }
   }

   private static class eo {
      IDInstruction q;
      IDArrayElt RF;
      IDExpression xK;

      public eo(IDInstruction var1, IDArrayElt var2, IDExpression var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }
   }

   private class nI {
      private IDInstruction RF;
      private List xK;
      private Map Dw;

      nI(IDInstruction var2) {
         this.RF = var2;
      }

      Collection q() {
         return (Collection)(this.Dw == null ? Collections.emptyList() : Collections.unmodifiableCollection(this.Dw.values()));
      }

      boolean RF() {
         IDNewArrayInfo var1 = this.RF.getAssignSource().asNewArrayInfo();
         IDVar var2 = this.RF.getAssignDestination().asVar();
         IJavaType var3 = var1.getType().getArrayTypeDimensionBelow();
         if (this.q(this.RF, var2, var3, var1.getInitialValues())) {
            return true;
         } else {
            if (cgx.this.RF && this.xK != null && !this.xK.isEmpty()) {
               boolean var4 = false;
               if (bws.xK(cgx.this.g) && Boolean.TRUE.equals(cgx.this.ctx.getData("unvirtualized"))) {
                  var4 = true;
               } else if (this.xK.size() == 1) {
                  var4 = true;
               }

               if (var4) {
                  for (IDInstruction var6 : this.xK) {
                     Couple var7 = cgx.this.cfg.getInstructionLocation(var6.getOffset());
                     if (var7 != null) {
                        cbf var8 = new cbf(100, 100, 10);
                        var8.assignLocalFields(cgx.this.ctx);
                        if (var8.q((BasicBlock)var7.getFirst(), (Integer)var7.getSecond(), var6)) {
                           return true;
                        }
                     }
                  }
               }
            }

            return false;
         }
      }

      private boolean q(IDInstruction var1, IDVar var2, IJavaType var3, List var4) {
         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();
         if (!this.q(var1, var2, var5, var6)) {
            return false;
         } else if (var5.isEmpty()) {
            return false;
         } else {
            TreeMap var7 = new TreeMap();

            for (int var8 = 0; var8 < var4.size(); var8++) {
               IDExpression var9 = (IDExpression)var4.get(var8);
               if (var9.getVarIds().contains(var2.getId())) {
                  return false;
               }

               var7.put(var8, var9.getType());
            }

            int var28 = 0;
            ArrayList var29 = new ArrayList();
            Iterator var10 = var5.iterator();

            while (true) {
               if (!var10.hasNext()) {
                  if (!var29.isEmpty()) {
                     if (var28 > var29.size() * 5) {
                        this.xK = var29;
                     }

                     return false;
                  }

                  for (cgx.CU var34 : var6) {
                     boolean var38 = false;

                     for (int var48 : var7.keySet()) {
                        if (var48 >= var34.Uv && var48 < var34.Uv + var34.oW) {
                           var38 = true;
                           break;
                        }
                     }

                     if (var38) {
                        DUtil.modifyInstructionSizes(cgx.this.ctx, var2x -> var2x == var34.q ? 1 + var7.size() : null);
                        IDInstruction var44 = var34.q;
                        Couple var49 = cgx.this.cfg.getInstructionLocation(var44.getOffset());
                        BasicBlock var15 = (BasicBlock)var49.getFirst();
                        int var16 = (Integer)var49.getSecond() + 1;
                        int var17 = var7.size();
                        var44.adjustSize(-var17);
                        long var18 = var34.q.getOffsetEnd();

                        for (int var21 : var7.keySet()) {
                           if (var21 >= var34.Uv && var21 < var34.Uv + var34.oW) {
                              IDImm var22 = cgx.this.g.createInt(var21);
                              IJavaType var23 = (IJavaType)var7.get(var21);
                              int var24 = var21 - var34.Uv;
                              IDImm var25 = this.q(var34.RF, var34.xK + var24);
                              IDArrayElt var26 = cgx.this.ctx.createArrayElt(var34.Dw, var22, var23);
                              IDInstruction var27 = cgx.this.ctx.createAssign(var26, var25);
                              var27.setOffset(var18);
                              var18++;
                              var17--;
                              var5.add(new cgx.eo(var27, var26, var27));
                              var15.add(var16, var27);
                              var16++;
                              var44 = var27;
                           }
                        }

                        var44.adjustSize(var17);
                     }
                  }

                  this.Dw = new TreeMap();

                  for (int var35 : var7.keySet()) {
                     IDVar var39 = cgx.this.ctx.createVirtualVar((IJavaType)var7.get(var35));
                     this.Dw.put(var35, var39);
                  }

                  for (cgx.eo var36 : var5) {
                     IDArrayElt var40 = var36.RF;
                     int var45 = (int)var40.getIndex().asImm().getRawValue();
                     IDVar var50 = (IDVar)this.Dw.get(var45);
                     var36.xK.replaceSubExpression(var40, var50);
                  }

                  DUtil.modifyInstructionSizes(cgx.this.ctx, var2x -> var2x == var1 ? 1 + this.Dw.size() : null);
                  Couple var33 = cgx.this.cfg.getInstructionLocation(var1.getOffset());
                  BasicBlock var37 = (BasicBlock)var33.getFirst();
                  int var41 = (Integer)var33.getSecond();
                  var1.setSize(1);
                  var41++;
                  long var46 = var1.getOffset() + 1L;

                  for (int var52 : this.Dw.keySet()) {
                     IDVar var53 = (IDVar)this.Dw.get(var52);
                     Object var54;
                     if (var52 < var4.size()) {
                        var54 = ((IDExpression)var4.get(var52)).duplicate();
                     } else {
                        var54 = cgx.this.g.createImm(0L, var53.getType());
                     }

                     IDInstruction var19 = cgx.this.ctx.createAssign(var53, (IDExpression)var54).withOffset(var46);
                     var37.add(var41, var19);
                     var41++;
                     var46++;
                  }

                  cgx.this.cfg.invalidateDataFlowAnalysis();
                  return true;
               }

               cgx.eo var11 = (cgx.eo)var10.next();
               IDArrayElt var12 = var11.RF;
               IDExpression var13 = var12.getIndex();
               if (!var13.isConstantImm()) {
                  if (!var13.isVar()) {
                     break;
                  }

                  IDInstruction var47 = var11.q;
                  if (!var47.isAssign() || !(var47.getAssignDestination() instanceof IDArrayElt) || !(var47.getAssignSource() instanceof IDImm)) {
                     break;
                  }

                  var29.add(var47);
               } else {
                  var28++;
                  int var14 = (int)var13.asImm().getRawValue();
                  if (!var7.containsKey(var14)) {
                     if (var14 < 0) {
                        return false;
                     }

                     var7.put(var14, var12.getType());
                  }
               }
            }

            return false;
         }
      }

      private boolean q(IDInstruction var1, IDVar var2, List var3, List var4) {
         IDFA var5 = cgx.this.cfg.doDataFlowAnalysis();
         HashSet var6 = new HashSet();
         ArrayDeque var7 = new ArrayDeque();
         var7.add(new Couple(var1, var2));
         HashSet var8 = new HashSet();
         var8.add(var1.getOffset());

         while (!var7.isEmpty()) {
            Couple var9 = (Couple)var7.remove();
            IDInstruction var10 = (IDInstruction)var9.getFirst();
            IDVar var11 = (IDVar)var9.getSecond();
            if (var6.add(new bww(var10.getOffset(), var11.getId()))) {
               for (long var13 : var5.getDefUses(var10.getOffset(), var11.getId())) {
                  Collection var15 = var5.getUseDefs(var13, var11.getId(), var8.size() + 1);
                  if (!var8.containsAll(var15)) {
                     return false;
                  }

                  IDInstruction var16 = (IDInstruction)cgx.this.cfg.getInstruction(var13);
                  if (!var16.visitDepthPost(new cgy(this, var11, var7, var8, var4, var16, var3))) {
                     return false;
                  }
               }
            }
         }

         return true;
      }

      Object q(IDExpression var1) {
         if (var1 instanceof IDCallInfo var2) {
            String var3 = var2.getMethodSignature();
            if (var3.equals("Ljava/lang/String;->getBytes()[B")) {
               IDExpression var4 = var2.getArgument(0);
               if (var4.isStringImm()) {
                  String var5 = var4.asImm().getStringValue(cgx.this.g);
                  return Strings.encodeUTF8(var5);
               }
            }
         } else if (var1 instanceof IDNewArrayInfo var9 && var9.getSize() instanceof IDImm) {
            for (IDExpression var12 : var9.getInitialValues()) {
               if (!var12.isConstantImm()) {
                  return null;
               }
            }

            int var11 = (int)var9.getSize().asImm().getValueAsLong();
            int var13 = 0;
            String var14 = var9.getType().getSignature();
            if (var14.equals("[Z")) {
               boolean[] var21 = new boolean[var11];

               for (IDExpression var35 : var9.getInitialValues()) {
                  var21[var13++] = (var35.asImm().getRawValue() & 1L) == 1L;
               }

               return var21;
            }

            if (var14.equals("[B")) {
               byte[] var20 = new byte[var11];

               for (IDExpression var34 : var9.getInitialValues()) {
                  var20[var13++] = (byte)var34.asImm().getRawValue();
               }

               return var20;
            }

            if (var14.equals("[S")) {
               short[] var19 = new short[var11];

               for (IDExpression var33 : var9.getInitialValues()) {
                  var19[var13++] = (short)var33.asImm().getRawValue();
               }

               return var19;
            }

            if (var14.equals("[C")) {
               char[] var18 = new char[var11];

               for (IDExpression var32 : var9.getInitialValues()) {
                  var18[var13++] = (char)var32.asImm().getRawValue();
               }

               return var18;
            }

            if (var14.equals("[I")) {
               int[] var17 = new int[var11];

               for (IDExpression var31 : var9.getInitialValues()) {
                  var17[var13++] = (int)var31.asImm().getRawValue();
               }

               return var17;
            }

            if (var14.equals("[J")) {
               long[] var16 = new long[var11];

               for (IDExpression var30 : var9.getInitialValues()) {
                  var16[var13++] = var30.asImm().getRawValue();
               }

               return var16;
            }

            if (var14.equals("[F")) {
               float[] var15 = new float[var11];

               for (IDExpression var29 : var9.getInitialValues()) {
                  var15[var13++] = Float.intBitsToFloat((int)var29.asImm().getRawValue());
               }

               return var15;
            }

            if (!var14.equals("[D")) {
               return null;
            }

            double[] var6 = new double[var11];

            for (IDExpression var8 : var9.getInitialValues()) {
               var6[var13++] = Double.longBitsToDouble(var8.asImm().getRawValue());
            }

            return var6;
         }

         return null;
      }

      boolean q(Object var1, int var2, int var3) {
         if (var2 >= 0 && var3 >= 0) {
            int var4 = Array.getLength(var1);
            return var2 < var4 && var2 + var3 <= var4;
         } else {
            return false;
         }
      }

      IDImm q(Object var1, int var2) {
         if (var1 instanceof boolean[]) {
            return cgx.this.g.createBoolean(Array.getBoolean(var1, var2));
         } else if (var1 instanceof byte[]) {
            return cgx.this.g.createByte(Array.getByte(var1, var2));
         } else if (var1 instanceof short[]) {
            return cgx.this.g.createShort(Array.getShort(var1, var2));
         } else if (var1 instanceof char[]) {
            return cgx.this.g.createChar(Array.getChar(var1, var2));
         } else if (var1 instanceof int[]) {
            return cgx.this.g.createInt(Array.getInt(var1, var2));
         } else if (var1 instanceof long[]) {
            return cgx.this.g.createLong(Array.getLong(var1, var2));
         } else if (var1 instanceof float[]) {
            return cgx.this.g.createFloat(Array.getFloat(var1, var2));
         } else if (var1 instanceof double[]) {
            return cgx.this.g.createDouble(Array.getDouble(var1, var2));
         } else {
            throw new RuntimeException();
         }
      }
   }
}
