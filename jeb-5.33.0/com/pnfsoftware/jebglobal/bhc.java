package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class bhc {
   private static final ILogger pC = GlobalLog.getLogger(bhc.class);
   private com.pnfsoftware.jeb.corei.parsers.dex.vi A;
   private bgy kS;
   private Set wS;
   private Set UT;
   private Set E;

   public bhc(IDexUnit var1) {
      this.A = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1;
   }

   private void pC() {
      this.kS = this.A.eP();
      this.wS = new LinkedHashSet();
      this.UT = new HashSet();
      this.E = new HashSet();
   }

   public List pC(IDexMethod var1, boolean var2) {
      this.pC();
      if (!this.A(var1)) {
         this.A((bfu)var1, var1.getClassType().getIndex(), null);
      }

      if (!var2) {
         this.wS.remove(var1.getIndex());
      }

      return new ArrayList(this.wS);
   }

   public List A(IDexMethod var1, boolean var2) {
      this.pC();
      if (!this.A(var1)) {
         this.pC((bfu)var1, var1.getClassType().getIndex(), null);
      }

      if (!var2) {
         this.wS.remove(var1.getIndex());
      }

      return new ArrayList(this.wS);
   }

   public List kS(IDexMethod var1, boolean var2) {
      this.pC();
      if (!this.A(var1)) {
         this.pC((bfu)var1, var1.getClassType().getIndex(), null);
         this.A((bfu)var1, var1.getClassType().getIndex(), null);
      }

      if (!var2) {
         this.wS.remove(var1.getIndex());
      }

      return new ArrayList(this.wS);
   }

   public List pC(IDexMethod var1) {
      if (this.A(var1)) {
         return Arrays.asList(var1.getIndex());
      } else {
         this.pC();
         ArrayList var2 = new ArrayList();
         var2.add(var1.getClassType().getIndex());

         while (!var2.isEmpty()) {
            LinkedHashSet var3 = new LinkedHashSet();

            for (int var5 : var2) {
               this.pC((bfu)var1, var5, var3);
            }

            var2.clear();
            LinkedHashSet var10 = new LinkedHashSet();

            for (int var6 : var3) {
               bhe var7 = this.kS.pC(var6);
               if (var7 != null) {
                  for (int var9 : var7.UT()) {
                     this.A((bfu)var1, var9, var10);
                  }
               }
            }

            var2.addAll(var10);
         }

         return new ArrayList(this.wS);
      }
   }

   private void pC(bfu var1, int var2, Set var3) {
      if (this.E.add(var2)) {
         bhe var4 = this.kS.pC(var2);
         if (var4 != null) {
            int var5 = this.pC(var4, var1);
            if (var5 >= 0 && this.wS.add(var5) && var3 != null) {
               var3.add(var2);
            }

            for (int var7 : var4.wS()) {
               this.pC(var1, var7, var3);
            }
         }
      }
   }

   private void A(bfu var1, int var2, Set var3) {
      if (this.UT.add(var2)) {
         bhe var4 = this.kS.pC(var2);
         if (var4 != null) {
            int var5 = this.pC(var4, var1);
            if (var5 >= 0 && this.wS.add(var5) && var3 != null) {
               var3.add(var2);
            }

            for (int var7 : var4.UT()) {
               this.A(var1, var7, var3);
            }
         }
      }
   }

   private int pC(bhe var1, bfu var2) {
      if (var2.wS().getIndex() == var1.pC) {
         return var2.getIndex();
      } else {
         for (int var4 : var1.UT) {
            bfu var5 = this.A.sY(var4);
            if (var5.A() == null || !var5.A().isPrivate()) {
               String var6 = var2.getName(false);
               String var7 = var5.getName(false);
               if (var6.equals(var7)) {
                  int[] var8 = var2.UT().getParameterTypeIndexes();
                  int[] var9 = var5.UT().getParameterTypeIndexes();
                  if (Arrays.equals(var8, var9)) {
                     return var4;
                  }
               }
            }
         }

         return -1;
      }
   }

   private boolean A(IDexMethod var1) {
      return Strings.isContainedIn(var1.getName(false), "<init>", "<clinit>");
   }
}
