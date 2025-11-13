package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.CannotImportTypeException;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

@SerDisabled
public class ayw {
   private static final ILogger pC = GlobalLog.getLogger(ayw.class);
   private ayy A;
   private ayw.Av kS;
   private boolean wS;
   private Deque UT;
   private int E;

   public ayw(ayy var1) {
      this(var1, null, true);
   }

   private ayw(ayy var1, ayw.Av var2, boolean var3) {
      this.A = var1;
      this.kS = var2 != null ? var2 : new ayw.Av(var1);
      this.wS = var3;
   }

   public int pC() {
      return this.E;
   }

   public aye pC(aye var1) {
      if (this.UT != null) {
         throw new IllegalStateException();
      } else {
         this.UT = new ArrayDeque();
         aye var2 = this.A(var1);
         Assert.a(this.UT.isEmpty());
         if (this.wS) {
            while (this.kS.pC()) {
               IdentityHashMap var3 = new IdentityHashMap();

               for (aye var6 : this.kS.A()) {
                  aye var7 = new ayw(this.A, this.kS, false).pC(var6);
                  var3.put(var6, var7);
               }

               for (aye var9 : var3.keySet()) {
                  this.kS.pC(var9, (aye)var3.get(var9));
               }
            }
         }

         return var2;
      }
   }

   private aye A(aye var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Cannot import null type");
      } else if (var1.kS() == this.A) {
         pC.warn("Useless attempt to import a local type: %s", var1);
         return var1;
      } else if (var1 instanceof ays var15) {
         aye var17 = this.A(var15.sY());
         ArrayList var23 = new ArrayList();

         for (aye var36 : var15.getParameterTypes()) {
            aye var42 = this.A(var36);
            var23.add(var42);
         }

         ICallingConvention var30 = var15.getCallingConvention();
         ICallingConvention var37 = this.A.A().getConvention(var30.getName());
         if (var37 == null) {
            throw new CannotImportTypeException("Cannot import calling convention: " + var30.getName());
         } else {
            this.E++;
            return this.A.pC(var37, var17, var23, var15.getPrototypeAttributes());
         }
      } else {
         String var2 = var1.getSignature(false);
         aye var3 = this.A.pC(var2, false);
         if (var3 != null) {
            return var3;
         } else {
            this.UT.add(var1);

            try {
               if (var1 instanceof ayq) {
                  throw new RuntimeException("Primitive type cannot be imported: " + var1);
               } else {
                  if (var1 instanceof ayt var4) {
                     aye var5 = var4.E();
                     int var6 = var4.getReferenceCount();
                     aye var7 = this.A(var5);
                     if (var7 == null) {
                        var3 = this.kS.pC(var5, var6);
                     } else {
                        var3 = this.A.pC((INativeType)var7, var6);
                     }
                  } else if (var1 instanceof ayg var18) {
                     aye var24 = var18.E();
                     int var31 = var18.getElementCount();
                     aye var38 = this.A(var24);
                     if (var38 == null) {
                        var3 = this.kS.A(var24, var31);
                     } else {
                        var3 = this.A.A((INativeType)var38, var31);
                     }
                  } else if (var1 instanceof ayf var19) {
                     aye var25 = var19.E();
                     aye var32 = this.A(var25);
                     if (var32 == null) {
                        var3 = this.kS.pC(var19.getSignature(false), var25);
                     } else {
                        var3 = this.A.pC(var19.getSignature(false), var32);
                     }
                  } else if (var1 instanceof aym var20) {
                     aym var26 = this.A.pC(var20.getSignature(false));

                     for (ayl var39 : var20.getConstants()) {
                        var26.pC(var39.getName(), var39.getValue());
                     }

                     var3 = var26;
                  } else {
                     if (!(var1 instanceof ayv)) {
                        throw new RuntimeException("Don't know how to import type object: " + var1);
                     }

                     int var21 = 0;

                     for (aye var34 : this.UT) {
                        if (var34 instanceof ayv) {
                           var21++;
                        }

                        if (var34 instanceof ayt && var21 > 0) {
                           return null;
                        }
                     }

                     ayv var28 = (ayv)var1;
                     ayv var35 = this.A.pC(var28.getSignature(false), var28.getPadding(), var28.getAlignment());
                     ArrayList var41 = new ArrayList();

                     for (ayu var9 : var28.getFields()) {
                        aye var10 = var9.pC();
                        aye var11 = this.A(var10);
                        var41.add(var11);
                     }

                     int var43 = 0;

                     for (ayu var45 : var28.getFields()) {
                        if (var35.pC(
                              var45.getName(), (INativeType)var41.get(var43), var45.getOffset(), var45.getBitsize(), var45.getAlignment(), var45.getFlags()
                           )
                           == null) {
                           throw new CannotImportTypeException(Strings.ff("Cannot register field %s to imported structure %s", var45, var28));
                        }

                        var43++;
                     }

                     var3 = var35;
                  }

                  this.E++;
                  return var3;
               }
            } finally {
               Assert.a(this.UT.removeLast() == var1);
            }
         }
      }
   }

   static class Av {
      ayy pC;
      private IdentityHashMap A = new IdentityHashMap();
      private IdentityHashMap kS = new IdentityHashMap();
      private IdentityHashMap wS = new IdentityHashMap();

      public Av(ayy var1) {
         this.pC = var1;
      }

      boolean pC() {
         return !this.A.isEmpty() || !this.kS.isEmpty() || !this.wS.isEmpty();
      }

      Set A() {
         IdentityHashSet var1 = new IdentityHashSet();
         var1.addAll(this.A.keySet());
         var1.addAll(this.kS.keySet());
         var1.addAll(this.wS.keySet());
         return var1;
      }

      ayt pC(aye var1, int var2) {
         Object var3 = (List)this.A.get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.A.put(var1, var3);
         }

         for (ayt var5 : var3) {
            if (var5.getReferenceCount() == var2) {
               return var5;
            }
         }

         ayt var6 = this.pC.pC(null, var2);
         var3.add(var6);
         return var6;
      }

      ayg A(aye var1, int var2) {
         Object var3 = (List)this.kS.get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.kS.put(var1, var3);
         }

         for (ayg var5 : var3) {
            if (var5.getElementCount() == var2) {
               return var5;
            }
         }

         ayg var6 = this.pC.A(null, var2);
         var3.add(var6);
         return var6;
      }

      ayf pC(String var1, aye var2) {
         Object var3 = (List)this.wS.get(var2);
         if (var3 == null) {
            var3 = new ArrayList();
            this.wS.put(var2, var3);
         }

         String var4 = TypeUtil.normalizeSignature(false, var1, null, null);

         for (ayf var6 : var3) {
            if (var4.equals(var6.getSignature(false))) {
               return var6;
            }
         }

         ayf var7 = this.pC.A(var1, null);
         var3.add(var7);
         return var7;
      }

      void pC(aye var1, aye var2) {
         List var3 = (List)this.A.get(var1);
         if (var3 != null) {
            for (ayt var5 : var3) {
               this.pC.pC(var5, var2);
            }

            this.A.remove(var1);
         }

         List var8 = (List)this.kS.get(var1);
         if (var8 != null) {
            for (ayg var6 : var8) {
               this.pC.pC(var6, var2);
            }

            this.kS.remove(var1);
         }

         List var10 = (List)this.wS.get(var1);
         if (var10 != null) {
            for (ayf var7 : var10) {
               this.pC.pC(var7, var2);
            }

            this.wS.remove(var1);
         }
      }
   }
}
