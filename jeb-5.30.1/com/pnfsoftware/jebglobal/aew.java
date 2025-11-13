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

public class aew {
   public static boolean q(ICOperation var0) {
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

   public static boolean q(ICExpression var0, ICExpression var1) {
      Boolean var2 = q(var0, var1, aew.eo.q);
      return BooleanUtils.toBooleanDefaultIfNull(var2, false);
   }

   public static Boolean q(ICPredicate var0, ICPredicate var1) {
      return q(var0, var1, aew.eo.RF);
   }

   private static Boolean q(ICExpression var0, ICExpression var1, aew.eo var2) {
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
                     case q:
                        return q(var6, var10, var9, var12);
                     case RF:
                        return RF(var6, var10, var9, var12);
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

   private static boolean q(COperatorType var0, long var1, COperatorType var3, long var4) {
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

   private static Boolean RF(COperatorType var0, long var1, COperatorType var3, long var4) {
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

   private static BigInteger RF(Range var0) {
      return var0.lowerBoundType() == BoundType.CLOSED ? (BigInteger)var0.lowerEndpoint() : ((BigInteger)var0.lowerEndpoint()).add(BigInteger.ONE);
   }

   private static BigInteger xK(Range var0) {
      return var0.upperBoundType() == BoundType.CLOSED ? (BigInteger)var0.upperEndpoint() : ((BigInteger)var0.upperEndpoint()).subtract(BigInteger.ONE);
   }

   public static boolean q(Range var0) {
      return var0.hasLowerBound() && var0.hasUpperBound() && RF(var0).equals(xK(var0));
   }

   public static boolean q(Set var0) {
      if (var0.size() != 2) {
         return false;
      } else {
         Iterator var1 = var0.iterator();
         Range var2 = (Range)var1.next();
         Range var3 = (Range)var1.next();
         return !var2.hasLowerBound() && !var3.hasUpperBound() && xK(var2).add(BigInteger.valueOf(2L)).equals(RF(var3));
      }
   }

   public static ICExpression q(aew.CU var0, ICMethod var1, ICConstantFactory var2) {
      var0.Uv();
      if (var0.Dw.isEmpty()) {
         return var2.createInt32(0);
      } else {
         Set var3 = var0.Dw.asRanges();
         Iterator var4 = var3.iterator();
         Range var5 = (Range)var4.next();
         if (var3.size() == 1 && !var5.hasLowerBound() && !var5.hasUpperBound()) {
            return var2.createInt32(1);
         } else if (var0.q() > 1 && q(var3)) {
            return CUtil.ne(var1, var0.xK(), var2.createInt(xK(var5).add(BigInteger.ONE), var0.Dw()));
         } else if (var0.q() <= var0.RF()) {
            return null;
         } else if (var3.size() == 1) {
            return q(var0, var5, var1, var2);
         } else {
            ArrayList var6 = new ArrayList();

            for (Range var8 : var3) {
               ICExpression var9 = q(var0, var8, var1, var2);
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

   private static ICExpression q(aew.CU var0, Range var1, ICMethod var2, ICConstantFactory var3) {
      if (q(var1)) {
         return CUtil.eq(var2, var0.xK(), var3.createInt(RF(var1), var0.Dw()));
      } else {
         ICOperation var4 = null;
         if (var1.hasUpperBound()) {
            if (var1.upperBoundType() == BoundType.CLOSED) {
               var4 = CUtil.le(var2, var0.xK(), var3.createInt((BigInteger)var1.upperEndpoint(), var0.Dw()));
            } else {
               var4 = CUtil.lt(var2, var0.xK(), var3.createInt((BigInteger)var1.upperEndpoint(), var0.Dw()));
            }

            if (!var1.hasLowerBound()) {
               return var4;
            }
         }

         ICOperation var5 = null;
         if (var1.hasLowerBound()) {
            if (var1.lowerBoundType() == BoundType.CLOSED) {
               var5 = CUtil.ge(var2, var0.xK(), var3.createInt((BigInteger)var1.lowerEndpoint(), var0.Dw()));
            } else {
               var5 = CUtil.gt(var2, var0.xK(), var3.createInt((BigInteger)var1.lowerEndpoint(), var0.Dw()));
            }

            if (!var1.hasUpperBound()) {
               return var5;
            }
         }

         return CUtil.andL(var2, var5, var4);
      }
   }

   public static class CU {
      private ICExpression q;
      private int RF;
      private int xK = 1;
      private TreeRangeSet Dw = TreeRangeSet.create();

      private CU(ICExpression var1, int var2, Range var3) {
         this.q = var1;
         this.RF = var2;
         if (var3 != null) {
            this.Dw.add(var3);
         }
      }

      private CU(ICExpression var1, int var2) {
         this(var1, var2, null);
      }

      private CU(aew.CU var1, aew.CU var2) {
         this(var1.q, var1.RF, null);
         this.xK = var1.xK + var2.xK;
      }

      public static aew.CU q(COperatorType var0, ICExpression var1, ICConstantInteger var2) {
         BigInteger var3 = var2.getIntegerValue();
         int var4 = var2.getBitsize();
         aew.CU var5 = null;
         switch (var0) {
            case EQ:
               return new aew.CU(var1, var4, Range.singleton(var3));
            case NE:
               var5 = new aew.CU(var1, var4);
               var5.Dw.add(Range.greaterThan(var3));
               var5.Dw.add(Range.lessThan(var3));
            default:
               return var5;
            case GE:
               return new aew.CU(var1, var4, Range.atLeast(var3));
            case GT:
               return new aew.CU(var1, var4, Range.greaterThan(var3));
            case LE:
               return new aew.CU(var1, var4, Range.atMost(var3));
            case LT:
               return new aew.CU(var1, var4, Range.lessThan(var3));
         }
      }

      public int q() {
         return this.xK;
      }

      public int RF() {
         int var1 = 0;

         for (Range var3 : this.Dw.asRanges()) {
            if (aew.q(var3)) {
               var1++;
            } else {
               var1 += (var3.hasLowerBound() ? 1 : 0) + (var3.hasUpperBound() ? 1 : 0);
            }
         }

         return var1;
      }

      public ICExpression xK() {
         return this.q;
      }

      public int Dw() {
         return this.RF;
      }

      private aew.CU xK(aew.CU var1) {
         if (var1 != null && this.q.equals(var1.q)) {
            this.Dw.addAll(var1.Dw);
            this.xK = this.xK + var1.xK;
            return this;
         } else {
            return null;
         }
      }

      public aew.CU q(aew.CU var1) {
         if (!this.q.equals(var1.q)) {
            return null;
         } else {
            TreeRangeSet var2 = TreeRangeSet.create();

            for (Range var4 : var1.Dw.asRanges()) {
               RangeSet var5 = this.Dw.subRangeSet(var4);
               var2.addAll(var5);
            }

            this.Dw = var2;
            this.xK = this.xK + var1.xK;
            return this;
         }
      }

      @Override
      public String toString() {
         return this.RF + ":" + this.q + "(" + this.xK + ") = " + this.oW();
      }

      private CharSequence oW() {
         StringBuilder var1 = new StringBuilder();

         for (Range var3 : this.Dw.asRanges()) {
            var1.append(var3.lowerBoundType() == BoundType.CLOSED ? "[" : "]")
               .append(Strings.safe(var3.lowerEndpoint()))
               .append("::")
               .append(Strings.safe(var3.upperEndpoint()))
               .append(var3.upperBoundType() == BoundType.CLOSED ? "]" : "[")
               .append(",");
         }

         return var1;
      }

      public static aew.CU q(ICExpression var0) {
         return var0 instanceof ICOperation ? q((ICOperation)var0) : null;
      }

      public static aew.CU q(ICOperation var0) {
         switch (var0.getOperatorType()) {
            case EQ:
            case NE:
            case GE:
            case GT:
            case LE:
            case LT:
               if (var0.getFirstOperand() instanceof ICIdentifier && var0.getSecondOperand() instanceof ICConstantInteger) {
                  ICConstantInteger var3 = (ICConstantInteger)var0.getSecondOperand();
                  return q(var0.getOperatorType(), var0.getFirstOperand(), var3);
               }
            default:
               return null;
            case LOG_AND:
            case LOG_OR:
               aew.CU var1 = q(var0.getFirstOperand());
               if (var1 == null) {
                  return null;
               } else {
                  aew.CU var2 = q(var0.getSecondOperand());
                  if (var2 == null) {
                     return null;
                  } else {
                     return var0.getOperatorType() == COperatorType.LOG_AND ? var1.q(var2) : var1.xK(var2);
                  }
               }
         }
      }

      public TreeRangeSet Uv() {
         TreeRangeSet var1 = TreeRangeSet.create();

         for (Range var3 : this.Dw.asRanges()) {
            var1.add(var3.canonical(DiscreteDomain.bigIntegers()));
         }

         if (var1.asRanges().size() < this.Dw.asRanges().size()) {
            this.Dw = var1;
         }

         return var1;
      }

      public boolean RF(aew.CU var1) {
         TreeRangeSet var2 = this.Uv();
         TreeRangeSet var3 = var1.Uv();

         for (Range var5 : var2.asRanges()) {
            if (var3.intersects(var5)) {
               return true;
            }
         }

         return false;
      }
   }

   private static enum eo {
      q,
      RF;
   }
}
