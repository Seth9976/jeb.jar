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
public class bbw {
   private static final ILogger q = GlobalLog.getLogger(bbw.class);
   private bby RF;
   private bbw.eo xK;
   private boolean Dw;
   private Deque Uv;
   private int oW;

   public bbw(bby var1) {
      this(var1, null, true);
   }

   private bbw(bby var1, bbw.eo var2, boolean var3) {
      this.RF = var1;
      this.xK = var2 != null ? var2 : new bbw.eo(var1);
      this.Dw = var3;
   }

   public int q() {
      return this.oW;
   }

   public bbd q(bbd var1) {
      if (this.Uv != null) {
         throw new IllegalStateException();
      } else {
         this.Uv = new ArrayDeque();
         bbd var2 = this.RF(var1);
         Assert.a(this.Uv.isEmpty());
         if (this.Dw) {
            while (this.xK.q()) {
               IdentityHashMap var3 = new IdentityHashMap();

               for (bbd var6 : this.xK.RF()) {
                  bbd var7 = new bbw(this.RF, this.xK, false).q(var6);
                  var3.put(var6, var7);
               }

               for (bbd var9 : var3.keySet()) {
                  this.xK.q(var9, (bbd)var3.get(var9));
               }
            }
         }

         return var2;
      }
   }

   private bbd RF(bbd var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Cannot import null type");
      } else if (var1.xK() == this.RF) {
         q.warn("Useless attempt to import a local type: %s", var1);
         return var1;
      } else if (var1 instanceof bbs var15) {
         bbd var17 = this.RF(var15.nf());
         ArrayList var23 = new ArrayList();

         for (bbd var36 : var15.getParameterTypes()) {
            bbd var42 = this.RF(var36);
            var23.add(var42);
         }

         ICallingConvention var30 = var15.getCallingConvention();
         ICallingConvention var37 = this.RF.Dw().getConvention(var30.getName());
         if (var37 == null) {
            throw new CannotImportTypeException("Cannot import calling convention: " + var30.getName());
         } else {
            this.oW++;
            return this.RF.q(var37, var17, var23, var15.getPrototypeAttributes());
         }
      } else {
         String var2 = var1.getSignature(false);
         bbd var3 = this.RF.q(var2, false);
         if (var3 != null) {
            return var3;
         } else {
            this.Uv.add(var1);

            try {
               if (var1 instanceof bbq) {
                  throw new RuntimeException("Primitive type cannot be imported: " + var1);
               } else {
                  if (var1 instanceof bbt var4) {
                     bbd var5 = var4.oW();
                     int var6 = var4.getReferenceCount();
                     bbd var7 = this.RF(var5);
                     if (var7 == null) {
                        var3 = this.xK.q(var5, var6);
                     } else {
                        var3 = this.RF.q((INativeType)var7, var6);
                     }
                  } else if (var1 instanceof bbf var18) {
                     bbd var24 = var18.oW();
                     int var31 = var18.getElementCount();
                     bbd var38 = this.RF(var24);
                     if (var38 == null) {
                        var3 = this.xK.RF(var24, var31);
                     } else {
                        var3 = this.RF.RF((INativeType)var38, var31);
                     }
                  } else if (var1 instanceof bbe var19) {
                     bbd var25 = var19.oW();
                     bbd var32 = this.RF(var25);
                     if (var32 == null) {
                        var3 = this.xK.q(var19.getSignature(false), var25);
                     } else {
                        var3 = this.RF.q(var19.getSignature(false), var32);
                     }
                  } else if (var1 instanceof bbm var20) {
                     bbm var26 = this.RF.q(var20.getSignature(false));

                     for (bbl var39 : var20.getConstants()) {
                        var26.q(var39.getName(), var39.getValue());
                     }

                     var3 = var26;
                  } else {
                     if (!(var1 instanceof bbv)) {
                        throw new RuntimeException("Don't know how to import type object: " + var1);
                     }

                     int var21 = 0;

                     for (bbd var34 : this.Uv) {
                        if (var34 instanceof bbv) {
                           var21++;
                        }

                        if (var34 instanceof bbt && var21 > 0) {
                           return null;
                        }
                     }

                     bbv var28 = (bbv)var1;
                     bbv var35 = this.RF.q(var28.getSignature(false), var28.getPadding(), var28.getAlignment());
                     ArrayList var41 = new ArrayList();

                     for (bbu var9 : var28.getFields()) {
                        bbd var10 = var9.q();
                        bbd var11 = this.RF(var10);
                        var41.add(var11);
                     }

                     int var43 = 0;

                     for (bbu var45 : var28.getFields()) {
                        if (var35.q(
                              var45.getName(), (INativeType)var41.get(var43), var45.getOffset(), var45.getBitsize(), var45.getAlignment(), var45.getFlags()
                           )
                           == null) {
                           throw new CannotImportTypeException(Strings.ff("Cannot register field %s to imported structure %s", var45, var28));
                        }

                        var43++;
                     }

                     var3 = var35;
                  }

                  this.oW++;
                  return var3;
               }
            } finally {
               Assert.a(this.Uv.removeLast() == var1);
            }
         }
      }
   }

   static class eo {
      bby q;
      private IdentityHashMap RF = new IdentityHashMap();
      private IdentityHashMap xK = new IdentityHashMap();
      private IdentityHashMap Dw = new IdentityHashMap();

      public eo(bby var1) {
         this.q = var1;
      }

      boolean q() {
         return !this.RF.isEmpty() || !this.xK.isEmpty() || !this.Dw.isEmpty();
      }

      Set RF() {
         IdentityHashSet var1 = new IdentityHashSet();
         var1.addAll(this.RF.keySet());
         var1.addAll(this.xK.keySet());
         var1.addAll(this.Dw.keySet());
         return var1;
      }

      bbt q(bbd var1, int var2) {
         Object var3 = (List)this.RF.get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.RF.put(var1, var3);
         }

         for (bbt var5 : var3) {
            if (var5.getReferenceCount() == var2) {
               return var5;
            }
         }

         bbt var6 = this.q.q(null, var2);
         var3.add(var6);
         return var6;
      }

      bbf RF(bbd var1, int var2) {
         Object var3 = (List)this.xK.get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.xK.put(var1, var3);
         }

         for (bbf var5 : var3) {
            if (var5.getElementCount() == var2) {
               return var5;
            }
         }

         bbf var6 = this.q.RF(null, var2);
         var3.add(var6);
         return var6;
      }

      bbe q(String var1, bbd var2) {
         Object var3 = (List)this.Dw.get(var2);
         if (var3 == null) {
            var3 = new ArrayList();
            this.Dw.put(var2, var3);
         }

         String var4 = TypeUtil.normalizeSignature(false, var1, null, null);

         for (bbe var6 : var3) {
            if (var4.equals(var6.getSignature(false))) {
               return var6;
            }
         }

         bbe var7 = this.q.RF(var1, null);
         var3.add(var7);
         return var7;
      }

      void q(bbd var1, bbd var2) {
         List var3 = (List)this.RF.get(var1);
         if (var3 != null) {
            for (bbt var5 : var3) {
               this.q.q(var5, var2);
            }

            this.RF.remove(var1);
         }

         List var8 = (List)this.xK.get(var1);
         if (var8 != null) {
            for (bbf var6 : var8) {
               this.q.q(var6, var2);
            }

            this.xK.remove(var1);
         }

         List var10 = (List)this.Dw.get(var1);
         if (var10 != null) {
            for (bbe var7 : var10) {
               this.q.q(var7, var2);
            }

            this.Dw.remove(var1);
         }
      }
   }
}
