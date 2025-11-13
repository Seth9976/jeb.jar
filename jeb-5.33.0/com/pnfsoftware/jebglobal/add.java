package com.pnfsoftware.jebglobal;

import com.google.common.collect.BoundType;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.BooleanUtils;

public class add {
   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static boolean pC(ICOperation var0) {
      COperatorType var1 = var0.getOperatorType();
      switch (var1) {
         case EQ:
         case NE:
         case GE:
         case GT:
         case LE:
         case LT:
            return true;
         default:
            return false;
      }
   }

   public static boolean pC(ICExpression var0, ICExpression var1) {
      Boolean var2 = pC(var0, var1, add.Av.pC);
      return BooleanUtils.toBooleanDefaultIfNull(var2, false);
   }

   public static Boolean pC(ICPredicate var0, ICPredicate var1) {
      return pC(var0, var1, add.Av.A);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static Boolean pC(ICExpression var0, ICExpression var1, add.Av var2) {
      if (var0 instanceof ICPredicate) {
         var0 = ((ICPredicate)var0).getExpression();
      }

      if (var1 instanceof ICPredicate) {
         var1 = ((ICPredicate)var1).getExpression();
      }

      if (!(var0 instanceof ICOperation)) {
         return null;
      } else if (!(var1 instanceof ICOperation)) {
         return null;
      } else {
         ICOperation var3 = (ICOperation)var0;
         ICExpression var4 = var3.getFirstOperand();
         ICExpression var5 = var3.getSecondOperand();
         COperatorType var6 = var3.getOperatorType();
         ICOperation var7 = (ICOperation)var1;
         ICExpression var8 = var7.getSecondOperand();
         if (var3.getFirstOperand().getElementType() != CElementType.Identifier) {
            return null;
         } else if (!var7.getFirstOperand().equals(var4)) {
            return null;
         } else if (var5 != null && var8 != null) {
            if (var5.getElementType() == CElementType.Constant && var8.getElementType() == CElementType.Constant) {
               COperatorType var9 = var7.getOperatorType();
               if (var5 instanceof ICConstantInteger && var8 instanceof ICConstantInteger) {
                  long var10 = ((ICConstantInteger)var5).getValueAsLong();
                  long var12 = ((ICConstantInteger)var8).getValueAsLong();
                  switch (var2) {
                     case pC:
                        return pC(var6, var10, var9, var12);
                     case A:
                        return A(var6, var10, var9, var12);
                     default:
                        throw new RuntimeException("TBI");
                  }
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static boolean pC(COperatorType var0, long var1, COperatorType var3, long var4) {
      switch (var0) {
         case EQ:
            return var3 == var0 && var1 == var4;
         case NE:
            switch (var3) {
               case EQ:
                  return var1 != var4;
               case NE:
               default:
                  return false;
               case GE:
                  return var4 > var1;
               case GT:
                  return var4 >= var1;
               case LE:
                  return var4 < var1;
               case LT:
                  return var4 <= var1;
            }
         case GE:
            switch (var3) {
               case EQ:
               case GE:
               case GT:
                  return var4 >= var1;
               case NE:
               default:
                  return false;
            }
         case GT:
            switch (var3) {
               case EQ:
               case LE:
                  return var4 > var1;
               case NE:
               case GE:
               default:
                  return false;
               case GT:
                  return var4 >= var1;
            }
         case LE:
            switch (var3) {
               case EQ:
               case LE:
               case LT:
                  return var4 <= var1;
               default:
                  return false;
            }
         case LT:
            switch (var3) {
               case EQ:
               case LE:
                  return var4 < var1;
               case LT:
                  return var4 <= var1;
               default:
                  return false;
            }
         default:
            return false;
      }
   }

   private static Boolean A(COperatorType var0, long var1, COperatorType var3, long var4) {
      COperatorType[] var6 = new COperatorType[]{COperatorType.LE, COperatorType.LT, COperatorType.GE, COperatorType.GT, COperatorType.EQ, COperatorType.NE};
      int var7 = -1;
      int var8 = -1;

      for (int var9 = 0; var9 < var6.length; var9++) {
         COperatorType var10 = var6[var9];
         if (var10 == var0) {
            var7 = var9;
         }

         if (var10 == var3) {
            var8 = var9;
         }
      }

      if (var7 != -1 && var8 != -1) {
         if (var8 < var7) {
            var0 = var6[var8];
            var3 = var6[var7];
            long var11 = var1;
            var1 = var4;
            var4 = var11;
         }

         switch (var0) {
            case EQ:
               switch (var3) {
                  case EQ:
                     return var1 == var4;
                  case NE:
                     return var1 != var4;
                  default:
                     return null;
               }
            case NE:
               switch (var3) {
                  case NE:
                     return true;
                  default:
                     return null;
               }
            case GE:
               switch (var3) {
                  case EQ:
                     return var4 >= var1;
                  case NE:
                  case GE:
                  case GT:
                     return true;
                  default:
                     return null;
               }
            case GT:
               switch (var3) {
                  case EQ:
                     return var4 > var1;
                  case NE:
                  case GT:
                     return true;
                  case GE:
                  default:
                     return null;
               }
            case LE:
               switch (var3) {
                  case EQ:
                     return var4 <= var1;
                  case NE:
                  case LE:
                  case LT:
                     return true;
                  case GE:
                     return var4 <= var1;
                  case GT:
                     return var4 < var1;
                  default:
                     return null;
               }
            case LT:
               switch (var3) {
                  case EQ:
                     return var4 < var1;
                  case NE:
                  case LT:
                     return true;
                  case GE:
                     return var4 < var1;
                  case GT:
                     return var4 < var1 - 1L;
                  case LE:
                  default:
                     return null;
               }
            default:
               return false;
         }
      } else {
         return null;
      }
   }

   private static BigInteger A(Range var0) {
      return var0.lowerBoundType() == BoundType.CLOSED ? (BigInteger)var0.lowerEndpoint() : ((BigInteger)var0.lowerEndpoint()).add(BigInteger.ONE);
   }

   private static BigInteger kS(Range var0) {
      return var0.upperBoundType() == BoundType.CLOSED ? (BigInteger)var0.upperEndpoint() : ((BigInteger)var0.upperEndpoint()).subtract(BigInteger.ONE);
   }

   public static boolean pC(Range var0) {
      return var0.hasLowerBound() && var0.hasUpperBound() && A(var0).equals(kS(var0));
   }

   public static boolean pC(Set var0) {
      if (var0.size() != 2) {
         return false;
      } else {
         Iterator var1 = var0.iterator();
         Range var2 = (Range)var1.next();
         Range var3 = (Range)var1.next();
         return !var2.hasLowerBound() && !var3.hasUpperBound() && kS(var2).add(BigInteger.valueOf(2L)).equals(A(var3));
      }
   }

   public static ICExpression pC(add.Sv var0, ICMethod var1, ICConstantFactory var2) {
      var0.UT();
      if (var0.wS.isEmpty()) {
         return var2.createInt32(0);
      } else {
         Set var3 = var0.wS.asRanges();
         Iterator var4 = var3.iterator();
         Range var5 = (Range)var4.next();
         if (var3.size() == 1 && !var5.hasLowerBound() && !var5.hasUpperBound()) {
            return var2.createInt32(1);
         } else if (var0.pC() > 1 && pC(var3)) {
            return CUtil.ne(var1, var0.kS(), var2.createInt(kS(var5).add(BigInteger.ONE), var0.wS()));
         } else if (var0.pC() <= var0.A()) {
            return null;
         } else if (var3.size() == 1) {
            return pC(var0, var5, var1, var2);
         } else {
            ArrayList var6 = new ArrayList();

            for (Range var8 : var3) {
               ICExpression var9 = pC(var0, var8, var1, var2);
               if (var9 == null) {
                  return null;
               }

               var6.add(var9);
            }

            if (var6.size() == 1) {
               return (ICExpression)var6.get(0);
            } else {
               Object var10 = (ICExpression)var6.get(0);

               for (int var11 = 1; var11 < var6.size(); var11++) {
                  var10 = CUtil.orL(var1, (ICExpression)var10, (ICExpression)var6.get(var11));
               }

               return (ICExpression)var10;
            }
         }
      }
   }

   private static ICExpression pC(add.Sv var0, Range var1, ICMethod var2, ICConstantFactory var3) {
      if (pC(var1)) {
         return CUtil.eq(var2, var0.kS(), var3.createInt(A(var1), var0.wS()));
      } else {
         ICOperation var4 = null;
         if (var1.hasUpperBound()) {
            if (var1.upperBoundType() == BoundType.CLOSED) {
               var4 = CUtil.le(var2, var0.kS(), var3.createInt((BigInteger)var1.upperEndpoint(), var0.wS()));
            } else {
               var4 = CUtil.lt(var2, var0.kS(), var3.createInt((BigInteger)var1.upperEndpoint(), var0.wS()));
            }

            if (!var1.hasLowerBound()) {
               return var4;
            }
         }

         ICOperation var5 = null;
         if (var1.hasLowerBound()) {
            if (var1.lowerBoundType() == BoundType.CLOSED) {
               var5 = CUtil.ge(var2, var0.kS(), var3.createInt((BigInteger)var1.lowerEndpoint(), var0.wS()));
            } else {
               var5 = CUtil.gt(var2, var0.kS(), var3.createInt((BigInteger)var1.lowerEndpoint(), var0.wS()));
            }

            if (!var1.hasUpperBound()) {
               return var5;
            }
         }

         return CUtil.andL(var2, var5, var4);
      }
   }

   private static enum Av {
      pC,
      A;
   }

   public static class Sv {
      private ICExpression pC;
      private int A;
      private int kS = 1;
      private TreeRangeSet wS = TreeRangeSet.create();

      private Sv(ICExpression var1, int var2, Range var3) {
         this.pC = var1;
         this.A = var2;
         if (var3 != null) {
            this.wS.add(var3);
         }
      }

      private Sv(ICExpression var1, int var2) {
         this(var1, var2, null);
      }

      public static add.Sv pC(COperatorType var0, ICExpression var1, ICConstantInteger var2) {
         BigInteger var3 = var2.getIntegerValue();
         int var4 = var2.getBitsize();
         add.Sv var5 = null;
         switch (var0) {
            case EQ:
               return new add.Sv(var1, var4, Range.singleton(var3));
            case NE:
               var5 = new add.Sv(var1, var4);
               var5.wS.add(Range.greaterThan(var3));
               var5.wS.add(Range.lessThan(var3));
            default:
               return var5;
            case GE:
               return new add.Sv(var1, var4, Range.atLeast(var3));
            case GT:
               return new add.Sv(var1, var4, Range.greaterThan(var3));
            case LE:
               return new add.Sv(var1, var4, Range.atMost(var3));
            case LT:
               return new add.Sv(var1, var4, Range.lessThan(var3));
         }
      }

      public int pC() {
         return this.kS;
      }

      public int A() {
         int var1 = 0;

         for (Range var3 : this.wS.asRanges()) {
            if (add.pC(var3)) {
               var1++;
            } else {
               var1 += (var3.hasLowerBound() ? 1 : 0) + (var3.hasUpperBound() ? 1 : 0);
            }
         }

         return var1;
      }

      public ICExpression kS() {
         return this.pC;
      }

      public int wS() {
         return this.A;
      }

      private add.Sv kS(add.Sv var1) {
         if (var1 != null && this.pC.equals(var1.pC)) {
            this.wS.addAll(var1.wS);
            this.kS = this.kS + var1.kS;
            return this;
         } else {
            return null;
         }
      }

      public add.Sv pC(add.Sv var1) {
         if (!this.pC.equals(var1.pC)) {
            return null;
         } else {
            TreeRangeSet var2 = TreeRangeSet.create();

            for (Range var4 : var1.wS.asRanges()) {
               RangeSet var5 = this.wS.subRangeSet(var4);
               var2.addAll(var5);
            }

            this.wS = var2;
            this.kS = this.kS + var1.kS;
            return this;
         }
      }

      @Override
      public String toString() {
         return this.A + ":" + this.pC + "(" + this.kS + ") = " + this.E();
      }

      private CharSequence E() {
         StringBuilder var1 = new StringBuilder();

         for (Range var3 : this.wS.asRanges()) {
            var1.append(var3.lowerBoundType() == BoundType.CLOSED ? "[" : "]")
               .append(Strings.safe(var3.lowerEndpoint()))
               .append("::")
               .append(Strings.safe(var3.upperEndpoint()))
               .append(var3.upperBoundType() == BoundType.CLOSED ? "]" : "[")
               .append(",");
         }

         return var1;
      }

      public static add.Sv pC(ICExpression var0) {
         return var0 instanceof ICOperation ? pC((ICOperation)var0) : null;
      }

      public static add.Sv pC(ICOperation var0) {
         switch (var0.getOperatorType()) {
            case EQ:
            case NE:
            case GE:
            case GT:
            case LE:
            case LT:
               if (var0.getFirstOperand() instanceof ICIdentifier && var0.getSecondOperand() instanceof ICConstantInteger) {
                  ICConstantInteger var3 = (ICConstantInteger)var0.getSecondOperand();
                  return pC(var0.getOperatorType(), var0.getFirstOperand(), var3);
               }
            default:
               return null;
            case LOG_AND:
            case LOG_OR:
               add.Sv var1 = pC(var0.getFirstOperand());
               if (var1 == null) {
                  return null;
               } else {
                  add.Sv var2 = pC(var0.getSecondOperand());
                  if (var2 == null) {
                     return null;
                  } else {
                     return var0.getOperatorType() == COperatorType.LOG_AND ? var1.pC(var2) : var1.kS(var2);
                  }
               }
         }
      }

      public TreeRangeSet UT() {
         TreeRangeSet var1 = TreeRangeSet.create();

         for (Range var3 : this.wS.asRanges()) {
            var1.add(var3.canonical(DiscreteDomain.bigIntegers()));
         }

         if (var1.asRanges().size() < this.wS.asRanges().size()) {
            this.wS = var1;
         }

         return var1;
      }

      public boolean A(add.Sv var1) {
         TreeRangeSet var2 = this.UT();
         TreeRangeSet var3 = var1.UT();

         for (Range var5 : var2.asRanges()) {
            if (var3.intersects(var5)) {
               return true;
            }
         }

         return false;
      }
   }
}
