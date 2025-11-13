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

public class alo {
   private static final StructuredLogger E = aco.pC(alo.class);
   IERoutineContext pC;
   IWildcardTypeManager A;
   int kS;
   IEGeneric wS;
   IEGeneric UT;
   private boolean sY;
   private List ys;
   private INativeType ld;
   private int gp;
   private boolean oT;
   private boolean fI;
   private IEVar WR;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("Status:");
      Strings.ff(var1, "EXP= %d-bit @ %s %s\n", this.kS, this.wS, this.sY ? "[PURE REF.]" : "");
      if (this.ys != null) {
         Strings.ff(var1, "  TERMS= %s\n", this.ys);
         Strings.ff(var1, "  RAW.TYPE= %s\n", this.ld);
         Strings.ff(var1, "  CLEAN.ACCESS?= %s\n", this.pC());
         if (!this.pC()) {
            Strings.ff(var1, "    OFFSET= 0x%X\n", this.A());
            Strings.ff(var1, "    BITSIZES= type:%d access:%d\n", 8 * this.ld.getSize(), this.kS);
         }

         Strings.ff(var1, "  PVAR= %s\n", this.WR);
      }

      return var1.toString();
   }

   public alo(IERoutineContext var1, IEMem var2) {
      this(var1, var2.getBitsize(), var2.getReference(), var2);
   }

   public alo(IERoutineContext var1, int var2, IEGeneric var3, IEGeneric var4) {
      if (var1 != null && var3 != null) {
         this.pC = var1;
         this.A = var1.getWildcardTypeManager();
         this.kS = var2;
         this.wS = var3;
         this.UT = var4;
         this.sY = var4 == null;
      } else {
         throw new NullPointerException();
      }
   }

   public boolean pC() {
      return !this.fI && !this.oT;
   }

   public int A() {
      return this.gp;
   }

   public INativeType kS() {
      return this.ld;
   }

   public List wS() {
      return this.ys;
   }

   public boolean UT() {
      return this.ys != null && !this.ys.isEmpty();
   }

   public IEVar pC(ICMethod var1) {
      if (this.ys != null) {
         throw new IllegalStateException();
      } else {
         this.ys = new ArrayList();
         alo.Av var2 = new alo.Av(this.wS, this.UT, this.pC, var1);
         return !var2.pC(this.ys) ? null : this.pC(this.ys, var1);
      }
   }

   public IWildcardType E() {
      if (this.ys != null) {
         throw new IllegalStateException();
      } else {
         this.ys = new ArrayList();
         alo.Av var1 = new alo.Av(this.wS, this.UT, this.pC, null);
         if (!var1.pC(this.ys)) {
            return null;
         } else {
            IEVar var2 = this.pC(this.ys, null);
            return var2 == null ? null : var2.getType();
         }
      }
   }

   private IEVar pC(List var1, ICMethod var2) {
      INativeType var3 = null;
      IEGeneric var4 = null;

      for (alo.Ws var6 : var1) {
         if (var6.kS == 1L && (var6.A instanceof IEVar || !(var6.A instanceof IEImm))) {
            IWildcardType var7 = var6.A.getType();
            if (var7 != null) {
               INativeType var8 = var7.getNativeType();
               if (var8 != null) {
                  INativeType var9 = TypeUtil.getNonAlias(var8);
                  if (TypeUtil.isReference(var9) && !TypeUtil.isVoidPointer(var9)) {
                     var3 = var9;
                     var4 = var6.A;
                     var6.wS = 2;
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

         for (alo.Ws var29 : var1) {
            if (var29.wS == 0 && var29.A instanceof IEImm) {
               long var32 = ((IEImm)var29.A).getValueAsLong();
               var26 += (int)(var32 * var29.kS);
               var29.wS = 1;
            }
         }

         Object[] var10000 = new Object[]{var1};
         var10000 = new Object[]{var4};
         var10000 = new Object[]{var26};
         boolean var28 = true;
         if (var4 instanceof IEVar) {
            if (var4.asVar().isGlobalReference()) {
               var3 = ((ayt)var3).getPointedType();
            } else if (var4.asVar().isStackReference()) {
               var3 = ((ayt)var3).getPointedType();
               IEVar var30 = this.pC.getStackManager().getVariable(var4.asVar().getAddress());
               if (var30 == null) {
                  return null;
               }
            } else {
               if (this.kS == 0) {
                  return null;
               }

               var28 = false;
            }
         } else {
            if (this.kS == 0) {
               return null;
            }

            var28 = false;
         }

         alo.Sv var31 = new alo.Sv(var28, var3, var26);
         Couple var33 = var31.pC();
         if (var33 == null) {
            return null;
         } else {
            List var34 = var31.A();
            if (TypeUtil.isVoid((INativeType)var33.getFirst())) {
               return null;
            } else {
               this.ld = (INativeType)var33.getFirst();
               this.gp = (Integer)var33.getSecond();
               this.oT = this.gp != 0;
               this.fI = this.kS != 0 && this.kS != this.ld.getSize() * 8;
               this.pC(var34, this.gp);
               if (var2 == null) {
                  if (!this.oT && !this.fI) {
                     IWildcardType var35 = this.A.create(this.ld);
                     this.pC.acquireNativeItem(var35);
                     return aku.pC(var35.getBitsize(), var35, null);
                  } else {
                     return null;
                  }
               } else {
                  ICGlobalContext var10 = var2.getGlobalContext();
                  ICElementFactory var11 = var2.getElementFactory();
                  ICConstantFactory var12 = var2.getConstantFactory();
                  ICTypeFactory var13 = var2.getTypeFactory();
                  if (!this.pC(var1, var34)) {
                     return null;
                  } else {
                     this.pC(var34, this.gp);
                     Object var14;
                     if (var4 instanceof IEVar) {
                        var14 = (ICExpression)((IEVar)var4).generateC(this.pC, var2, 0, true);
                     } else {
                        if (var4 == this.wS) {
                           return null;
                        }

                        var14 = (ICExpression)var4.generateC(this.pC, var2);
                     }

                     boolean var15 = false;

                     for (int var16 = 0; var16 < var34.size(); var16++) {
                        alo.K var17 = (alo.K)var34.get(var16);
                        Object var18;
                        if (var17.pC()) {
                           IWildcardType var19 = this.A.create(var17.pC);
                           this.pC.acquireNativeItem(var19);
                           INativeFieldItem var20 = null;
                           if (var17.pC instanceof IClassType var21) {
                              INativeClassItem var22 = var21.getClassItem();
                              if (var22 != null) {
                                 for (INativeFieldItem var24 : var22.getInstanceFields()) {
                                    Couple var25 = var24.getStructureFieldDetails();
                                    if (var25 != null && var25.getFirst() == var17.pC && var25.getSecond() == var17.A) {
                                       var20 = var24;
                                       break;
                                    }
                                 }
                              }
                           }

                           ICField var47;
                           if (var20 == null) {
                              if (var17.A.getType() instanceof IStructureType && var17.A.isAnonymous()) {
                                 continue;
                              }

                              var47 = var10.getFieldFactory().createStructureField(var17.pC, var17.A);
                           } else {
                              var47 = var10.getFieldFactory().create(var20, false);
                           }

                           var18 = var11.createInstanceField(var47, (ICExpression)var14, var15);
                           var15 = false;
                        } else {
                           Object var41;
                           if (var17.UT == null) {
                              if (var16 == 0 && var34.size() >= 2) {
                                 if (var17.wS == 0 && ((alo.K)var34.get(1)).pC()) {
                                    var15 = true;
                                    continue;
                                 }

                                 if (var17.wS == 0 && ((alo.K)var34.get(1)).A()) {
                                    continue;
                                 }
                              }

                              var41 = var12.createInt32(var17.wS);
                           } else {
                              Object var44;
                              if (var17.wS == 0) {
                                 var44 = var17.UT;
                              } else {
                                 var44 = EUtil.add(var17.UT, this.pC.createImm(var17.wS, var17.UT.getBitsize()));
                              }

                              if (EUtil.isOperation((IEGeneric)var44, OperationType.CAST, OperationType.CAST_S)
                                 && ((IEGeneric)var44).getBitsize() == this.pC.getGlobalContext().getAddressBitsize()) {
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

                              var41 = (ICExpression)((IEGeneric)var44).generateC(this.pC, var2);
                           }

                           var18 = var11.createArrayElement((ICExpression)var14, (ICExpression)var41);
                        }

                        var14 = var18;
                     }

                     Object var37 = this.ld;
                     if (this.kS == 0) {
                        var14 = var11.createOperation(COperatorType.REF, (ICExpression)var14);
                        var37 = ((INativeType)var37).getTypeManager().createReference((INativeType)var37, 1);
                     }

                     if (this.oT || this.fI) {
                        Assert.a(this.kS != 0);
                        ITypeManager var38 = this.A.getNativeTypeManager();
                        var37 = var38.getInteger(this.kS / 8, true);
                        if (var37 == null) {
                           return null;
                        }

                        var14 = var11.createOperation(COperatorType.REF, (ICExpression)var14);
                        INativeType var40 = var38.getInteger(1, true);
                        if (this.oT) {
                           IReferenceType var42 = var38.createReference(var40);
                           ICType var45 = var13.create(this.A.create(var42));
                           var14 = var11.createCast(var45, (ICExpression)var14);
                           var14 = var11.createOperation(COperatorType.ADD, (ICExpression)var14, var12.createInt32(this.gp));
                        }

                        if (!this.oT || var37 != var40) {
                           IReferenceType var43 = var38.createReference((INativeType)var37);
                           ICType var46 = var13.create(this.A.create(var43));
                           var14 = var11.createCast(var46, (ICExpression)var14);
                        }

                        if (!this.sY) {
                           var14 = var11.createOperation(COperatorType.PTR, (ICExpression)var14);
                        }
                     } else if (this.sY) {
                        var14 = var11.createOperation(COperatorType.REF, (ICExpression)var14);
                     }

                     IWildcardType var39 = this.A.create((INativeType)var37);
                     this.pC.acquireNativeItem(var39);
                     this.WR = aku.pC(this.wS.getBitsize(), var39, (ICElement)var14);
                     return this.WR;
                  }
               }
            }
         }
      }
   }

   private void pC(List var1, int var2) {
      Object[] var10000 = new Object[0];

      for (alo.K var4 : var1) {
         var10000 = new Object[]{var4};
      }

      if (var2 > 0) {
         var10000 = new Object[]{var2};
      }
   }

   private boolean pC(List var1, List var2) {
      HashMap var3 = new HashMap();

      for (alo.Ws var5 : var1) {
         if (var5.wS == 0) {
            Long var6 = (Long)var3.get(var5.A);
            if (var6 == null) {
               var6 = 0L;
            }

            var3.put(var5.A, var6 + var5.kS);
         }
      }

      Iterator var17 = var2.iterator();

      while (true) {
         alo.K var18;
         int var19;
         while (true) {
            if (!var17.hasNext()) {
               return var3.isEmpty();
            }

            var18 = (alo.K)var17.next();
            if (var18.kS != null && var18.UT == null) {
               if (var18.kS instanceof IReferenceType) {
                  var19 = ((IReferenceType)var18.kS).getPointedType().getSize();
                  break;
               }

               if (var18.kS instanceof IArrayType) {
                  var19 = ((IArrayType)var18.kS).getElementType().getSize();
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
               var22 = EUtil.mul((IEGeneric)var22, this.pC.createImm(var12, ((IEGeneric)var22).getBitsize()));
            }

            if (var20 == null) {
               var20 = var22;
            } else {
               var20 = EUtil.add((IEGeneric)var20, (IEGeneric)var22);
            }
         }

         var18.UT = (IEGeneric)var20;
      }
   }

   private static class Av extends amn {
      IERoutineContext pC;
      ICMethod A;

      public Av(IEGeneric var1, IEGeneric var2, IERoutineContext var3, ICMethod var4) {
         super(var1, var2);
         this.pC = var3;
         this.A = var4;
      }

      protected alo.Ws pC(long var1, IEGeneric var3, IEGeneric var4) {
         return new alo.Ws(var1, var3, var4);
      }

      @Override
      protected IEGeneric pC(IEGeneric var1, IEGeneric var2) {
         if (var1 instanceof IEMem) {
            alo var3 = new alo(this.pC, (IEMem)var1);
            return var3.pC(this.A);
         } else {
            if (var1 instanceof IECompose) {
               if (this.A != null) {
                  alp var4 = new alp((IECompose)var1);
                  return var4.pC(this.pC, this.A);
               }
            } else if (var1 instanceof IEOperation) {
               return var1;
            }

            return null;
         }
      }
   }

   public static class K {
      IStructureType pC;
      IStructureTypeField A;
      INativeType kS;
      int wS;
      IEGeneric UT;

      public K(IStructureType var1, IStructureTypeField var2) {
         this.pC = var1;
         this.A = var2;
      }

      public K(INativeType var1, int var2) {
         if (!(var1 instanceof IArrayType) && !(var1 instanceof IReferenceType)) {
            throw new IllegalArgumentException();
         } else {
            this.kS = var1;
            this.wS = var2;
         }
      }

      public boolean pC() {
         return this.A != null;
      }

      public boolean A() {
         return this.kS != null;
      }

      @Override
      public String toString() {
         if (this.A != null) {
            return Strings.ff("FIELD= %s", this.A.getName());
         } else {
            return this.UT == null
               ? Strings.ff("ELEMENT= %s @@ [%d]", this.kS.getName(), this.wS)
               : Strings.ff("ELEMENT= %s @@ [%d + %s]", this.kS.getName(), this.wS, this.UT);
         }
      }
   }

   public static class Sv {
      private final boolean pC;
      private final INativeType A;
      private final int kS;
      private final List wS = new ArrayList();

      public Sv(boolean var1, INativeType var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      public Couple pC() {
         return this.pC(this.A, this.kS, 0);
      }

      public List A() {
         return this.wS;
      }

      private Couple pC(INativeType var1, int var2, int var3) {
         INativeType var4 = TypeUtil.getNonAlias(var1);
         if (var3 == 0 && !this.pC) {
            INativeType var12 = ((IReferenceType)var4).getPointedType();
            int var14 = var12.getSize();
            if (var14 == 0) {
               return new Couple(var4, var2);
            } else {
               int var15 = var2 / var14;
               this.wS.add(new alo.K(var4, var15));
               var2 -= var15 * var14;
               return this.pC(var12, var2, var3 + 1);
            }
         } else {
            if (var4 instanceof IStructureType) {
               IStructureTypeField var5 = ((IStructureType)var4).getFieldOver(var2);
               if (var5 != null) {
                  this.wS.add(new alo.K((IStructureType)var4, var5));
                  var2 -= var5.getOffset();
                  INativeType var6 = var5.getType();
                  return this.pC(var6, var2, var3 + 1);
               }
            } else {
               if (!(var4 instanceof IArrayType)) {
                  return new Couple(var1, var2);
               }

               INativeType var11 = ((IArrayType)var4).getElementType();
               int var13 = var11.getSize();
               int var7 = var2 / var13;
               if (var7 < ((IArrayType)var4).getElementCount()) {
                  this.wS.add(new alo.K(var4, var7));
                  var2 -= var7 * var13;
                  return this.pC(var11, var2, var3 + 1);
               }
            }

            return null;
         }
      }
   }

   public static class Ws implements anr {
      private IEGeneric pC;
      private IEGeneric A;
      private long kS;
      private int wS;

      public Ws(long var1, IEGeneric var3, IEGeneric var4) {
         this.kS = var1;
         this.A = var3;
         this.pC = var4;
      }

      public long pC() {
         return this.kS;
      }

      public IEGeneric A() {
         return this.A;
      }

      public IEGeneric kS() {
         return this.pC;
      }

      @Override
      public String toString() {
         return Strings.ff("%dx\"%s\"%s", this.kS, this.A, this.wS == 1 ? " (USED)" : (this.wS == 2 ? "(BASE)" : ""));
      }

      public static alo.Ws pC(List var0) {
         for (alo.Ws var2 : var0) {
            if (var2.wS == 2) {
               return var2;
            }
         }

         return null;
      }
   }
}
