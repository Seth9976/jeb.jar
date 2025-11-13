package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ant {
   private static final StructuredLogger oW = aeg.q(ant.class);
   IERoutineContext q;
   IWildcardTypeManager RF;
   int xK;
   IEGeneric Dw;
   IEGeneric Uv;
   private boolean gO;
   private List nf;
   private INativeType gP;
   private int za;
   private boolean lm;
   private boolean zz;
   private IEVar JY;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("Status:");
      Strings.ff(var1, "EXP= %d-bit @ %s %s\n", this.xK, this.Dw, this.gO ? "[PURE REF.]" : "");
      if (this.nf != null) {
         Strings.ff(var1, "  TERMS= %s\n", this.nf);
         Strings.ff(var1, "  RAW.TYPE= %s\n", this.gP);
         Strings.ff(var1, "  CLEAN.ACCESS?= %s\n", this.q());
         if (!this.q()) {
            Strings.ff(var1, "    OFFSET= 0x%X\n", this.Dw());
            Strings.ff(var1, "    BITSIZES= type:%d access:%d\n", 8 * this.gP.getSize(), this.xK);
         }

         Strings.ff(var1, "  PVAR= %s\n", this.JY);
      }

      return var1.toString();
   }

   public ant(IERoutineContext var1, IEMem var2) {
      this(var1, var2.getBitsize(), var2.getReference(), var2);
   }

   public ant(IERoutineContext var1, int var2, IEGeneric var3, IEGeneric var4) {
      if (var1 != null && var3 != null) {
         this.q = var1;
         this.RF = var1.getWildcardTypeManager();
         this.xK = var2;
         this.Dw = var3;
         this.Uv = var4;
         this.gO = var4 == null;
      } else {
         throw new NullPointerException();
      }
   }

   public boolean q() {
      return !this.zz && !this.lm;
   }

   public boolean RF() {
      return this.zz;
   }

   public boolean xK() {
      return this.lm;
   }

   public int Dw() {
      return this.za;
   }

   public INativeType Uv() {
      return this.gP;
   }

   public List oW() {
      return this.nf;
   }

   public boolean gO() {
      return this.nf != null && !this.nf.isEmpty();
   }

   public IEVar q(ICMethod var1) {
      if (this.nf != null) {
         throw new IllegalStateException();
      } else {
         this.nf = new ArrayList();
         ant.eo var2 = new ant.eo(this.Dw, this.Uv, this.q, var1);
         return !var2.q(this.nf) ? null : this.q(this.nf, var1);
      }
   }

   public IWildcardType nf() {
      if (this.nf != null) {
         throw new IllegalStateException();
      } else {
         this.nf = new ArrayList();
         ant.eo var1 = new ant.eo(this.Dw, this.Uv, this.q, null);
         if (!var1.q(this.nf)) {
            return null;
         } else {
            IEVar var2 = this.q(this.nf, null);
            return var2 == null ? null : var2.getType();
         }
      }
   }

   private IEVar q(List var1, ICMethod var2) {
      INativeType var3 = null;
      IEGeneric var4 = null;

      for (ant.ej var6 : var1) {
         if (var6.xK == 1L && (var6.RF instanceof IEVar || !(var6.RF instanceof IEImm))) {
            IWildcardType var7 = var6.RF.getType();
            if (var7 != null) {
               INativeType var8 = var7.getNativeType();
               if (var8 != null) {
                  INativeType var9 = TypeUtil.getNonAlias(var8);
                  if (TypeUtil.isReference(var9) && !TypeUtil.isVoidPointer(var9)) {
                     var3 = var9;
                     var4 = var6.RF;
                     var6.Dw = 2;
                     break;
                  }
               }
            }
         }
      }

      if (var3 == null) {
         return null;
      } else {
         int var26 = 0;

         for (ant.ej var29 : var1) {
            if (var29.Dw == 0 && var29.RF instanceof IEImm) {
               long var32 = ((IEImm)var29.RF).getValueAsLong();
               var26 += (int)(var32 * var29.xK);
               var29.Dw = 1;
            }
         }

         Object[] var10000 = new Object[]{var1};
         var10000 = new Object[]{var4};
         var10000 = new Object[]{var26};
         boolean var28 = true;
         if (var4 instanceof IEVar) {
            if (var4.asVar().isGlobalReference()) {
               var3 = ((bbt)var3).getPointedType();
            } else if (var4.asVar().isStackReference()) {
               var3 = ((bbt)var3).getPointedType();
               IEVar var30 = this.q.getStackManager().getVariable(var4.asVar().getAddress());
               if (var30 == null) {
                  return null;
               }
            } else {
               if (this.xK == 0) {
                  return null;
               }

               var28 = false;
            }
         } else {
            if (this.xK == 0) {
               return null;
            }

            var28 = false;
         }

         ant.CU var31 = new ant.CU(var28, var3, var26);
         Couple var33 = var31.q();
         if (var33 == null) {
            return null;
         } else {
            List var34 = var31.RF();
            if (TypeUtil.isVoid((INativeType)var33.getFirst())) {
               return null;
            } else {
               this.gP = (INativeType)var33.getFirst();
               this.za = (Integer)var33.getSecond();
               this.lm = this.za != 0;
               this.zz = this.xK != 0 && this.xK != this.gP.getSize() * 8;
               this.q(var34, this.za);
               if (var2 == null) {
                  if (!this.lm && !this.zz) {
                     IWildcardType var35 = this.RF.create(this.gP);
                     this.q.acquireNativeItem(var35);
                     return amy.q(var35.getBitsize(), var35, null);
                  } else {
                     return null;
                  }
               } else {
                  ICGlobalContext var10 = var2.getGlobalContext();
                  ICElementFactory var11 = var2.getElementFactory();
                  ICConstantFactory var12 = var2.getConstantFactory();
                  ICTypeFactory var13 = var2.getTypeFactory();
                  if (!this.q(var1, var34)) {
                     return null;
                  } else {
                     this.q(var34, this.za);
                     Object var14;
                     if (var4 instanceof IEVar) {
                        var14 = (ICExpression)((IEVar)var4).generateC(this.q, var2, 0, true);
                     } else {
                        if (var4 == this.Dw) {
                           return null;
                        }

                        var14 = (ICExpression)var4.generateC(this.q, var2);
                     }

                     boolean var15 = false;

                     for (int var16 = 0; var16 < var34.size(); var16++) {
                        ant.nI var17 = (ant.nI)var34.get(var16);
                        Object var18;
                        if (var17.q()) {
                           IWildcardType var19 = this.RF.create(var17.q);
                           this.q.acquireNativeItem(var19);
                           INativeFieldItem var20 = null;
                           if (var17.q instanceof IClassType var21) {
                              INativeClassItem var22 = var21.getClassItem();
                              if (var22 != null) {
                                 for (INativeFieldItem var24 : var22.getInstanceFields()) {
                                    Couple var25 = var24.getStructureFieldDetails();
                                    if (var25 != null && var25.getFirst() == var17.q && var25.getSecond() == var17.RF) {
                                       var20 = var24;
                                       break;
                                    }
                                 }
                              }
                           }

                           ICField var47;
                           if (var20 == null) {
                              if (var17.RF.getType() instanceof IStructureType && var17.RF.isAnonymous()) {
                                 continue;
                              }

                              var47 = var10.getFieldFactory().createStructureField(var17.q, var17.RF);
                           } else {
                              var47 = var10.getFieldFactory().create(var20, false);
                           }

                           var18 = var11.createInstanceField(var47, (ICExpression)var14, var15);
                           var15 = false;
                        } else {
                           Object var41;
                           if (var17.Uv == null) {
                              if (var16 == 0 && var34.size() >= 2) {
                                 if (var17.Dw == 0 && ((ant.nI)var34.get(1)).q()) {
                                    var15 = true;
                                    continue;
                                 }

                                 if (var17.Dw == 0 && ((ant.nI)var34.get(1)).RF()) {
                                    continue;
                                 }
                              }

                              var41 = var12.createInt32(var17.Dw);
                           } else {
                              Object var44;
                              if (var17.Dw == 0) {
                                 var44 = var17.Uv;
                              } else {
                                 var44 = EUtil.add(var17.Uv, this.q.createImm(var17.Dw, var17.Uv.getBitsize()));
                              }

                              if (EUtil.isOperation((IEGeneric)var44, OperationType.CAST, OperationType.CAST_S)
                                 && ((IEGeneric)var44).getBitsize() == this.q.getGlobalContext().getAddressBitsize()) {
                                 IEGeneric var48 = ((IEGeneric)var44).asOperation().getOperand1();
                                 if (var48.getType() != null
                                    && ((IEGeneric)var44).getBitsize() >= var48.getBitsize()
                                    && (
                                       var48.getType().isUnsigned() && ((IEGeneric)var44).isOperation(OperationType.CAST)
                                          || var48.getType().isSigned() && ((IEGeneric)var44).isOperation(OperationType.CAST_S)
                                    )) {
                                    var44 = var48;
                                 }
                              }

                              var41 = (ICExpression)((IEGeneric)var44).generateC(this.q, var2);
                           }

                           var18 = var11.createArrayElement((ICExpression)var14, (ICExpression)var41);
                        }

                        var14 = var18;
                     }

                     Object var37 = this.gP;
                     if (this.xK == 0) {
                        var14 = var11.createOperation(COperatorType.REF, (ICExpression)var14);
                        var37 = ((INativeType)var37).getTypeManager().createReference((INativeType)var37, 1);
                     }

                     if (this.lm || this.zz) {
                        Assert.a(this.xK != 0);
                        ITypeManager var38 = this.RF.getNativeTypeManager();
                        var37 = var38.getInteger(this.xK / 8, true);
                        if (var37 == null) {
                           return null;
                        }

                        var14 = var11.createOperation(COperatorType.REF, (ICExpression)var14);
                        INativeType var40 = var38.getInteger(1, true);
                        if (this.lm) {
                           IReferenceType var42 = var38.createReference(var40);
                           ICType var45 = var13.create(this.RF.create(var42));
                           var14 = var11.createCast(var45, (ICExpression)var14);
                           var14 = var11.createOperation(COperatorType.ADD, (ICExpression)var14, var12.createInt32(this.za));
                        }

                        if (!this.lm || var37 != var40) {
                           IReferenceType var43 = var38.createReference((INativeType)var37);
                           ICType var46 = var13.create(this.RF.create(var43));
                           var14 = var11.createCast(var46, (ICExpression)var14);
                        }

                        if (!this.gO) {
                           var14 = var11.createOperation(COperatorType.PTR, (ICExpression)var14);
                        }
                     } else if (this.gO) {
                        var14 = var11.createOperation(COperatorType.REF, (ICExpression)var14);
                     }

                     IWildcardType var39 = this.RF.create((INativeType)var37);
                     this.q.acquireNativeItem(var39);
                     this.JY = amy.q(this.Dw.getBitsize(), var39, (ICElement)var14);
                     return this.JY;
                  }
               }
            }
         }
      }
   }

   private void q(List var1, int var2) {
      Object[] var10000 = new Object[0];

      for (ant.nI var4 : var1) {
         var10000 = new Object[]{var4};
      }

      if (var2 > 0) {
         var10000 = new Object[]{var2};
      }
   }

   private boolean q(List var1, List var2) {
      HashMap var3 = new HashMap();

      for (ant.ej var5 : var1) {
         if (var5.Dw == 0) {
            Long var6 = (Long)var3.get(var5.RF);
            if (var6 == null) {
               var6 = 0L;
            }

            var3.put(var5.RF, var6 + var5.xK);
         }
      }

      Iterator var17 = var2.iterator();

      while (true) {
         ant.nI var18;
         int var19;
         while (true) {
            if (!var17.hasNext()) {
               return var3.isEmpty();
            }

            var18 = (ant.nI)var17.next();
            if (var18.xK != null && var18.Uv == null) {
               if (var18.xK instanceof IReferenceType) {
                  var19 = ((IReferenceType)var18.xK).getPointedType().getSize();
                  break;
               }

               if (var18.xK instanceof IArrayType) {
                  var19 = ((IArrayType)var18.xK).getElementType().getSize();
                  break;
               }
            }
         }

         HashMap var7 = new HashMap();
         HashMap var8 = new HashMap();

         for (IEGeneric var10 : var3.keySet()) {
            long var11 = (Long)var3.get(var10);
            if (var11 >= var19) {
               long var13 = var11 / var19;
               var7.put(var10, var13);
               long var15 = var11 - var13 * var19;
               if (var15 > 0L) {
                  var8.put(var10, var15);
               }
            } else {
               var8.put(var10, var11);
            }
         }

         var3 = var8;
         Object var20 = null;

         for (Object var22 : var7.keySet()) {
            long var12 = (Long)var7.get(var22);
            if (var12 >= 2L) {
               var22 = EUtil.mul((IEGeneric)var22, this.q.createImm(var12, ((IEGeneric)var22).getBitsize()));
            }

            if (var20 == null) {
               var20 = var22;
            } else {
               var20 = EUtil.add((IEGeneric)var20, (IEGeneric)var22);
            }
         }

         var18.Uv = (IEGeneric)var20;
      }
   }

   public static class CU {
      private final boolean q;
      private final INativeType RF;
      private final int xK;
      private final List Dw = new ArrayList();

      public CU(boolean var1, INativeType var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public Couple q() {
         return this.q(this.RF, this.xK, 0);
      }

      public List RF() {
         return this.Dw;
      }

      private Couple q(INativeType var1, int var2, int var3) {
         INativeType var4 = TypeUtil.getNonAlias(var1);
         if (var3 == 0 && !this.q) {
            INativeType var12 = ((IReferenceType)var4).getPointedType();
            int var14 = var12.getSize();
            if (var14 == 0) {
               return new Couple(var4, var2);
            } else {
               int var15 = var2 / var14;
               this.Dw.add(new ant.nI(var4, var15));
               var2 -= var15 * var14;
               return this.q(var12, var2, var3 + 1);
            }
         } else {
            if (var4 instanceof IStructureType) {
               IStructureTypeField var5 = ((IStructureType)var4).getFieldOver(var2);
               if (var5 != null) {
                  this.Dw.add(new ant.nI((IStructureType)var4, var5));
                  var2 -= var5.getOffset();
                  INativeType var6 = var5.getType();
                  return this.q(var6, var2, var3 + 1);
               }
            } else {
               if (!(var4 instanceof IArrayType)) {
                  return new Couple(var1, var2);
               }

               INativeType var11 = ((IArrayType)var4).getElementType();
               int var13 = var11.getSize();
               int var7 = var2 / var13;
               if (var7 < ((IArrayType)var4).getElementCount()) {
                  this.Dw.add(new ant.nI(var4, var7));
                  var2 -= var7 * var13;
                  return this.q(var11, var2, var3 + 1);
               }
            }

            return null;
         }
      }
   }

   public static class ej implements aqc {
      private IEGeneric q;
      private IEGeneric RF;
      private long xK;
      private int Dw;

      public ej(long var1, IEGeneric var3, IEGeneric var4) {
         this.xK = var1;
         this.RF = var3;
         this.q = var4;
      }

      @Override
      public long q() {
         return this.xK;
      }

      @Override
      public IEGeneric RF() {
         return this.RF;
      }

      @Override
      public IEGeneric xK() {
         return this.q;
      }

      @Override
      public String toString() {
         return Strings.ff("%dx\"%s\"%s", this.xK, this.RF, this.Dw == 1 ? " (USED)" : (this.Dw == 2 ? "(BASE)" : ""));
      }

      public static ant.ej q(List var0) {
         for (ant.ej var2 : var0) {
            if (var2.Dw == 2) {
               return var2;
            }
         }

         return null;
      }
   }

   private static class eo extends aou {
      IERoutineContext q;
      ICMethod RF;

      public eo(IEGeneric var1, IEGeneric var2, IERoutineContext var3, ICMethod var4) {
         super(var1, var2);
         this.q = var3;
         this.RF = var4;
      }

      protected ant.ej q(long var1, IEGeneric var3, IEGeneric var4) {
         return new ant.ej(var1, var3, var4);
      }

      @Override
      protected IEGeneric q(IEGeneric var1, IEGeneric var2) {
         if (var1 instanceof IEMem) {
            ant var3 = new ant(this.q, (IEMem)var1);
            return var3.q(this.RF);
         } else {
            if (var1 instanceof IECompose) {
               if (this.RF != null) {
                  anu var4 = new anu((IECompose)var1);
                  return var4.q(this.q, this.RF);
               }
            } else if (var1 instanceof IEOperation) {
               return var1;
            }

            return null;
         }
      }
   }

   public static class nI {
      IStructureType q;
      IStructureTypeField RF;
      INativeType xK;
      int Dw;
      IEGeneric Uv;

      public nI(IStructureType var1, IStructureTypeField var2) {
         this.q = var1;
         this.RF = var2;
      }

      public nI(INativeType var1, int var2) {
         if (!(var1 instanceof IArrayType) && !(var1 instanceof IReferenceType)) {
            throw new IllegalArgumentException();
         } else {
            this.xK = var1;
            this.Dw = var2;
         }
      }

      public boolean q() {
         return this.RF != null;
      }

      public boolean RF() {
         return this.xK != null;
      }

      @Override
      public String toString() {
         if (this.RF != null) {
            return Strings.ff("FIELD= %s", this.RF.getName());
         } else {
            return this.Uv == null
               ? Strings.ff("ELEMENT= %s @@ [%d]", this.xK.getName(), this.Dw)
               : Strings.ff("ELEMENT= %s @@ [%d + %s]", this.xK.getName(), this.Dw, this.Uv);
         }
      }
   }
}
