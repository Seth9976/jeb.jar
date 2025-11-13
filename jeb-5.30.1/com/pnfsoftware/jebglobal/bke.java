package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Ser
public class bke extends bkb {
   private static final ILogger gO = GlobalLog.getLogger(bke.class, Integer.MAX_VALUE);
   @SerId(1)
   private Map nf = new HashMap();
   @SerId(2)
   private List gP = new ArrayList();
   @SerId(3)
   private Map za = new HashMap();
   @SerId(4)
   private com.pnfsoftware.jeb.corei.parsers.dex.oL lm;
   @SerId(value = 5, deprecated = true)
   @Deprecated
   private boolean zz;
   @SerId(6)
   private int JY;
   @SerTransient
   private Set HF = new HashSet();
   private static final int LK = 10000;
   @SerTransient
   private ConcurrentHashMap io;
   private static final String[] qa = new String[]{
      "Ljava/lang/Object;", "Ljava/lang/String;", "Ljava/lang/Class;", "Ljava/lang/Enum;", "Ljava/lang/Throwable;", "Ljava/lang/Class;"
   };

   @SerCustomInitPostGraph
   private void zz() {
      this.io = null;
      com.pnfsoftware.jeb.corei.parsers.dex.Vj var1 = this.gO();
      var1.RF();
   }

   public bke(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("types", var1, var2);
      this.lm = this.q(null, "", false);
      this.zz();
      if (this.io != null) {
         var1.addListener(new bkf(this, var1));
      }
   }

   private com.pnfsoftware.jeb.corei.parsers.dex.oL xK(com.pnfsoftware.jeb.corei.parsers.dex.CU var1) {
      com.pnfsoftware.jeb.corei.parsers.dex.oL var2 = (com.pnfsoftware.jeb.corei.parsers.dex.oL)this.za.get(var1);
      if (var2 == null) {
         var2 = new com.pnfsoftware.jeb.corei.parsers.dex.oL(var1);
         this.za.put(var1, var2);
      }

      return var2;
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.oL q(com.pnfsoftware.jeb.corei.parsers.dex.CU var1) {
      return (com.pnfsoftware.jeb.corei.parsers.dex.oL)this.za.get(var1);
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.oL q(com.pnfsoftware.jeb.corei.parsers.dex.oL var1, String var2, boolean var3) {
      int var4 = this.gP.size();
      com.pnfsoftware.jeb.corei.parsers.dex.Vj var5 = new com.pnfsoftware.jeb.corei.parsers.dex.Vj(this.xK, var4, var2, var3);
      this.gP.add(var5);
      com.pnfsoftware.jeb.corei.parsers.dex.oL var6 = this.xK(var5);
      var6.q(var1);
      var6.q(!this.xK.RF(var5));
      return var6;
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.oL q(String var1) {
      return this.q(var1, true, true);
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.oL q(String var1, boolean var2) {
      return this.q(var1, var2, true);
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.oL q(String var1, boolean var2, boolean var3) {
      ArrayList var4 = new ArrayList();
      if (!DexUtil.isClassname(var1, true, var4, this.xK.RF())) {
         return null;
      } else if (var4.isEmpty()) {
         return null;
      } else {
         com.pnfsoftware.jeb.corei.parsers.dex.oL var5 = this.lm;

         for (String var7 : var4) {
            com.pnfsoftware.jeb.corei.parsers.dex.oL var8 = var5.q(var7);
            if (var8 == null) {
               var8 = this.q(var5, var7, var2);
            }

            var5 = var8;
         }

         if (var3) {
            this.xK.xK(2, var1);
         }

         return var5;
      }
   }

   public List oW() {
      return Collections.unmodifiableList(this.gP);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Vj gO() {
      return (com.pnfsoftware.jeb.corei.parsers.dex.Vj)this.lm.xK();
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Vj RF(int var1) {
      return (com.pnfsoftware.jeb.corei.parsers.dex.Vj)this.gP.get(var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Vj RF(String var1) {
      return this.RF(var1, true);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Vj RF(String var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else if (!var1.equals("") && !var1.equals("L/")) {
         ArrayList var3 = new ArrayList();
         if (!DexUtil.isClassname(var1, true, var3, this.xK.RF())) {
            var3.clear();
            if (var1.endsWith("/")) {
               var1 = var1.substring(0, var1.length() - 1) + ";";
            }

            if (!DexUtil.isInternalClassname(var1, true, var3, this.xK.RF())) {
               return null;
            }
         }

         if (var3.isEmpty()) {
            return null;
         } else {
            com.pnfsoftware.jeb.corei.parsers.dex.oL var4 = this.lm;

            for (String var6 : var3) {
               com.pnfsoftware.jeb.corei.parsers.dex.oL var7 = var4.q(var6, var2);
               if (var7 == null) {
                  return null;
               }

               var4 = var7;
            }

            return var4 == null ? null : (com.pnfsoftware.jeb.corei.parsers.dex.Vj)var4.xK();
         }
      } else {
         return this.gO();
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Vj Dw(String var1) {
      com.pnfsoftware.jeb.corei.parsers.dex.Vj var2 = this.RF(var1, false);
      if (var2 == null) {
         var2 = this.RF(var1, true);
      }

      return var2;
   }

   public boolean q(String var1, String var2) {
      return this.q(var1, var2, true);
   }

   public synchronized boolean q(String var1, String var2, boolean var3) {
      com.pnfsoftware.jeb.corei.parsers.dex.Vj var4 = this.RF(var1);
      return var4 == null ? false : var4.q(var2, var3, false);
   }

   public synchronized boolean q(String var1, String var2, boolean var3, boolean var4) {
      com.pnfsoftware.jeb.corei.parsers.dex.Vj var5 = this.RF(var1);
      if (var5 == null) {
         return false;
      } else {
         com.pnfsoftware.jeb.corei.parsers.dex.Vj var6 = this.RF(var2);
         return var6 == null ? false : this.q(var5, var6, 0, var3, var4);
      }
   }

   public synchronized boolean RF(String var1, String var2, boolean var3, boolean var4) {
      bjn var5 = this.xK.za(var1);
      if (var5 == null) {
         return false;
      } else {
         com.pnfsoftware.jeb.corei.parsers.dex.Vj var6 = this.RF(var2);
         return var6 == null ? false : this.q(var5, var6, 0, var3, var4);
      }
   }

   com.pnfsoftware.jeb.corei.parsers.dex.Vj RF(com.pnfsoftware.jeb.corei.parsers.dex.CU var1) {
      com.pnfsoftware.jeb.corei.parsers.dex.oL var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         for (com.pnfsoftware.jeb.corei.parsers.dex.oL var3 = var2.RF(); var3 != null; var3 = var3.RF()) {
            com.pnfsoftware.jeb.corei.parsers.dex.CU var4 = var3.xK();
            if (var4 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Vj) {
               return (com.pnfsoftware.jeb.corei.parsers.dex.Vj)var4;
            }
         }

         return null;
      }
   }

   com.pnfsoftware.jeb.corei.parsers.dex.oL q(com.pnfsoftware.jeb.corei.parsers.dex.oL var1) {
      if (var1 == null) {
         return null;
      } else {
         for (com.pnfsoftware.jeb.corei.parsers.dex.oL var2 = var1.RF(); var2 != null; var2 = var2.RF()) {
            com.pnfsoftware.jeb.corei.parsers.dex.CU var3 = var2.xK();
            if (var3 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Vj) {
               return var2;
            }
         }

         return null;
      }
   }

   public boolean q(com.pnfsoftware.jeb.corei.parsers.dex.CU var1, com.pnfsoftware.jeb.corei.parsers.dex.CU var2, int var3, boolean var4, boolean var5) {
      if (var1 != null && var2 != null && var2 != var1) {
         com.pnfsoftware.jeb.corei.parsers.dex.oL var6 = this.q(var1);
         if (var6 != null && var6.RF() != null && var6.RF().xK() != var2) {
            String var7 = var1.getName(true);

            for (com.pnfsoftware.jeb.corei.parsers.dex.oL var9 : this.q(var2).getChildren()) {
               if (var9.xK().getName(true).equals(var7)) {
                  gO.warn(S.L("Can not move %s to %s: destination already exists"), var7, var2.getSignature(true));
                  return false;
               }
            }

            String var17 = var1.getSignature(true);
            String var18 = var2.getSignature(true);
            Object[] var10000 = new Object[]{var17, var18};
            if (!(var1 instanceof bjn) || !(var2 instanceof bjp) && !(var2 instanceof bjn)) {
               if (!(var1 instanceof bjn) && !(var1 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Vj)
                  || !(var2 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Vj)) {
                  return false;
               }

               if (var1 instanceof bjn var19) {
                  boolean var23 = false;
                  if (var19.isMemberClass()) {
                     var19.q(var5);
                     var23 = true;
                  }

                  com.pnfsoftware.jeb.corei.parsers.dex.oL var27 = this.q(var19);
                  com.pnfsoftware.jeb.corei.parsers.dex.oL var31 = this.q(var2);
                  var27.q(null);
                  var31.RF(var27);

                  for (com.pnfsoftware.jeb.corei.parsers.dex.oL var37 : this.RF(var27)) {
                     var37.q(null);
                     var31.RF(var37);
                  }

                  if (var23 && !var19.RF()) {
                     bjt var36 = (bjt)this.q(var19.getClassTypeIndex());
                     var36.q(var36.getName(), var19.getName());
                  }
               } else {
                  com.pnfsoftware.jeb.corei.parsers.dex.Vj var20 = (com.pnfsoftware.jeb.corei.parsers.dex.Vj)var1;
                  com.pnfsoftware.jeb.corei.parsers.dex.oL var24 = this.q(var20);
                  com.pnfsoftware.jeb.corei.parsers.dex.oL var28 = this.q(var2);
                  var24.q(null);
                  var28.RF(var24);
               }
            } else {
               bjn var10 = (bjn)var1;
               if (var2 instanceof bjp) {
                  boolean var11 = (var3 & 1) != 0;
                  boolean var12 = (var3 & 2) != 0;
                  if (!var12 && !var11 && !this.q(var10, (bjp)var2)) {
                     var12 = true;
                  }

                  if (var10.isMemberClass()) {
                     var10.q(var5);
                  }

                  var10.RF(var2.getIndex(), !var12, var5);
                  bjt var13 = (bjt)this.q(var10.getClassTypeIndex());
                  bjt var14 = ((bjp)var2).Dw();
                  var13.q(var13.getName(), var14.getName() + "$1" + var10.getName());
               } else {
                  boolean var21 = (var3 & 1) != 0;
                  boolean var25 = (var3 & 2) != 0;
                  if (!var25 && !var21 && !this.q(var10, (bjn)var2)) {
                     var25 = true;
                  }

                  if (var10.isMemberClass()) {
                     var10.q(var5);
                  }

                  var10.q(((bjn)var2).getClassTypeIndex(), !var25, var5);
                  bjt var29 = (bjt)this.q(var10.getClassTypeIndex());
                  bjt var32 = (bjt)this.q(((bjn)var2).getClassTypeIndex());
                  var29.q(var29.getName(), var32.getName() + "$" + var10.getName());
               }

               com.pnfsoftware.jeb.corei.parsers.dex.oL var22 = this.q(var10);
               com.pnfsoftware.jeb.corei.parsers.dex.oL var26 = this.q(var2);
               var22.q(null);
               var26.RF(var22);
               com.pnfsoftware.jeb.corei.parsers.dex.oL var30 = this.q(var26);

               for (com.pnfsoftware.jeb.corei.parsers.dex.oL var16 : this.RF(var22)) {
                  var16.q(null);
                  var30.RF(var16);
               }
            }

            if (var4) {
               this.xK.q(5, var17, var18, var3);
            }

            this.xK.q(var5, new UnitChangeEventData(5, var2, var1));
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private List RF(com.pnfsoftware.jeb.corei.parsers.dex.oL var1) {
      if (!(var1.xK() instanceof bjn)) {
         throw new IllegalArgumentException();
      } else {
         bjt var2 = (bjt)this.q(((bjn)var1.xK()).getClassTypeIndex());
         com.pnfsoftware.jeb.corei.parsers.dex.oL var3 = this.q(var2).RF();
         ArrayList var4 = new ArrayList();
         HashSet var5 = new HashSet();
         this.q(var1, var5);

         for (int var7 : var5) {
            bjt var8 = (bjt)this.q(var7);
            com.pnfsoftware.jeb.corei.parsers.dex.oL var9 = this.q(var8);
            if (var9.RF() != var3) {
               throw new RuntimeException();
            }

            var4.add(var9);
         }

         return var4;
      }
   }

   private void q(com.pnfsoftware.jeb.corei.parsers.dex.oL var1, Collection var2) {
      com.pnfsoftware.jeb.corei.parsers.dex.CU var3 = var1.xK();
      if (var3 instanceof IDexClass) {
         int var4 = ((IDexClass)var3).getClassTypeIndex();
         var2.add(var4);
      }

      for (com.pnfsoftware.jeb.corei.parsers.dex.oL var5 : var1.getChildren()) {
         this.q(var5, var2);
      }
   }

   public synchronized boolean q(String var1, String var2, int var3, boolean var4, boolean var5) {
      return this.q(this.xK.LK(var1), this.xK.LK(var2), var3, var4, var5);
   }

   boolean q(bjn var1, bjp var2) {
      List var3 = (List)var1.getMethods().stream().filter(var0 -> var0.RF().isConstructor()).collect(Collectors.toList());
      if (var3.size() != 1) {
         return false;
      } else {
         IDexMethod var4 = (IDexMethod)var3.get(0);
         Collection var5 = this.xK.getCrossReferences(DexPoolType.METHOD, var4.getIndex());
         if (var5.size() != 1) {
            return false;
         } else {
            ICodeCoordinates var6 = this.xK.xK().q(((IDexAddress)var5.iterator().next()).getInternalAddress());
            return var6 instanceof InstructionCoordinates && ((InstructionCoordinates)var6).getMethodId() == var2.getIndex();
         }
      }
   }

   boolean q(bjn var1, bjn var2) {
      List var3 = (List)var1.getMethods().stream().filter(var0 -> var0.RF().isConstructor()).collect(Collectors.toList());
      if (var3.size() != 1) {
         return false;
      } else {
         IDexMethod var4 = (IDexMethod)var3.get(0);
         Collection var5 = this.xK.getCrossReferences(DexPoolType.METHOD, var4.getIndex());
         if (var5.size() != 1) {
            return false;
         } else {
            ICodeCoordinates var6 = this.xK.xK().q(((IDexAddress)var5.iterator().next()).getInternalAddress());
            if (!(var6 instanceof InstructionCoordinates)) {
               return false;
            } else {
               int var7 = ((InstructionCoordinates)var6).getMethodId();
               bjp var8 = this.xK.gO(var7);
               return var8.RF() != null && var8.RF().isConstructor() ? var8.getClassTypeIndex() == var2.getClassTypeIndex() : false;
            }
         }
      }
   }

   public bjt xK(int var1) {
      return this.Uv(this.xK.RF(var1));
   }

   public synchronized bjt Uv(String var1) {
      bjt var2 = (bjt)this.xK(var1);
      if (var2 != null) {
         return var2;
      } else {
         if (var1.equals("Ljava/lang/invoke/StringConcatFactory;")
            || var1.equals("Ljava/lang/invoke/LambdaMetaFactory;")
            || var1.equals("Ljava/lang/runtime/SwitchBootstraps;")) {
            JebCoreService.notifySilentExceptionToClient(new RuntimeException("Making use of unsupported dynamic methods factory: " + var1), this.xK);
         }

         int var3 = this.Uv.size();
         String var4 = null;
         if (this.HF == null) {
            this.HF = new HashSet();
         }

         try {
            if (var1.endsWith(";")) {
               int var5 = 0;
               char var11 = var1.charAt(var5++);

               while (var11 == '[') {
                  var11 = var1.charAt(var5++);
               }

               if (var11 != 'L') {
                  throw new JebRuntimeException("Expected a class signature, got: " + var1);
               }

               var4 = var1.substring(var5 - 1);
               if (!this.nf.containsKey(var4)) {
                  this.nf.put(var4, new ArrayList());
                  this.HF.add(var4);
               }
            }

            var2 = new bjt(this, var3, var1);
         } catch (RuntimeException var8) {
            gO.catchingSilent(var8);
            String var6 = Formatter.escapeString(var1, false);
            String var7 = Strings.ff("L_bad_type_%d;", var3);
            this.xK.logError(true, S.L("Illegal type: \"%s\" -> replaced by \"%s\""), var6, var7);
            var2 = new bjt(this, var3, var7);
            this.JY++;
         }

         this.Uv.add(var2);
         this.oW.put(var1, var2);
         if (var4 != null) {
            ((List)this.nf.get(var4)).add(var2);
         }

         return var2;
      }
   }

   public synchronized void nf() {
      for (String var4 : qa) {
         if (this.xK(var4) == null) {
            this.xK.q(var4, false);
            this.Uv(var4);
         }
      }

      if (this.HF == null) {
         this.HF = new HashSet();
      }

      for (bjn var16 : this.xK.getClasses()) {
         bjt var24 = (bjt)this.Uv.get(var16.getClassTypeIndex());
         if (var24.q(var16)) {
            this.xK(var16);
            this.HF.add(var16.getSignature(false));
         }
      }

      for (String var17 : this.HF) {
         if (this.xK(var17) == null) {
            this.xK.q(var17, false);
            this.Uv(var17);
         }
      }

      for (String var18 : this.nf.keySet()) {
         bjt var25 = (bjt)this.oW.get(var18);
         List var31 = (List)this.nf.get(var18);
         if (var25 != null && var31 != null) {
            for (bjt var6 : var31) {
               if (var6 != var25 && var6.getMasterType() == null) {
                  var25.q(var6);
                  var6.RF(var25);
               }
            }
         }
      }

      for (String var19 : this.HF) {
         String[] var26 = var19.substring(1, var19.length() - 1).split("/");
         com.pnfsoftware.jeb.corei.parsers.dex.oL var32 = this.lm;

         for (int var38 = 0; var38 < var26.length - 1; var38++) {
            String var41 = var26[var38];
            com.pnfsoftware.jeb.corei.parsers.dex.oL var7 = var32.q(var41);
            if (var7 == null) {
               var7 = this.q(var32, var41, false);
            }

            var32 = var7;
         }

         bjt var39 = (bjt)this.oW.get(var19);
         var32.RF(this.xK(var39));
         bjn var42 = var39.xK();
         if (var42 != null) {
            var32.RF(this.xK(var42));
         }
      }

      for (bjo var20 : this.xK.getFields()) {
         bjt var27 = var20.Dw();
         if (var27 == null) {
            throw new JebRuntimeException("Cannot retrieve type for field: " + var20);
         }

         bjn var33 = var27.xK();
         if (var33 != null) {
            this.xK(var20).q(this.q(var33));
         }
      }

      for (bjp var21 : this.xK.getMethods()) {
         bjt var28 = var21.Dw();
         if (var28 == null) {
            throw new JebRuntimeException("Cannot retrieve type for method: " + var21);
         }

         bjn var34 = var28.xK();
         if (var34 != null) {
            this.xK(var21).q(this.q(var34));
         }
      }

      for (bjn var22 : this.xK.getClasses()) {
         if (var22.isMemberClass()) {
            int var29 = var22.xK();
            if (var29 >= 0) {
               bjn var35 = ((bjt)this.Uv.get(var29)).xK();
               if (var35 != null) {
                  this.q(var22).q(this.q(var35));
               }
            } else {
               int var36 = var22.Dw();
               if (var36 >= 0) {
                  this.q(var22).q(this.q((com.pnfsoftware.jeb.corei.parsers.dex.CU)this.xK.gO(var36)));
                  this.xK.gO(var36).getClassTypeIndex();
               }
            }
         }
      }

      this.HF.clear();
      int var15 = this.oW("Ljava/lang/Enum;");
      if (var15 >= 0) {
         for (bjn var30 : this.xK.getClasses()) {
            if (var30.isEnumeration() && !var30.isMemberClass()) {
               int var37 = var30.getSuperTypeIndex();
               if (var37 != var15) {
                  bjt var40 = (bjt)this.q(var37);
                  if (var40 != null) {
                     bjn var43 = var40.xK();
                     if (var43 != null) {
                        if (!var43.isEnumeration() || var43.getSuperTypeIndex() != var15) {
                           RuntimeException var44 = new RuntimeException(
                              Strings.ff("Expected enum and custom constant for %s, %s", var30.getSignature(false), var43.getSignature(false))
                           );
                           JebCoreService.notifySilentExceptionToClient(var44);
                        } else if (this.q(var30, var43, 0, true, false)) {
                           this.xK.logTrace("Moved custom enumerated constant %s under enum %s", var30.getSignature(false), var43.getSignature(false));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public String q(int var1, boolean var2, boolean var3, boolean var4) {
      return var1 >= 0 && var1 < this.Uv.size() ? ((bjt)this.Uv.get(var1)).getSignature(var2, var3, var4) : null;
   }

   public String q(int var1, boolean var2, boolean var3) {
      return this.q(var1, var2, var3, true);
   }

   public String q(int var1, boolean var2) {
      return this.q(var1, var2, true, true);
   }

   public String Dw(int var1) {
      return this.q(var1, true);
   }

   public int oW(String var1) {
      bjt var2 = (bjt)this.oW.get(var1);
      return var2 == null ? -1 : var2.getIndex();
   }

   public Set gP() {
      return this.nf.keySet();
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.oL za() {
      return this.lm;
   }

   public bjt gO(String var1) {
      bjt var2 = (bjt)this.xK(var1);
      if (var2 != null) {
         return var2;
      } else if (var1.length() > 2 && var1.startsWith("L") && var1.endsWith(";")) {
         String[] var3 = var1.substring(1, var1.length() - 1).split("/");
         if (var3.length == 0) {
            return null;
         } else {
            if (this.io != null) {
               var2 = (bjt)this.io.get(var1);
               if (var2 != null) {
                  return var2;
               }
            }

            com.pnfsoftware.jeb.corei.parsers.dex.oL var4 = this.lm;

            for (int var5 = 0; var5 < var3.length - 1; var5++) {
               com.pnfsoftware.jeb.corei.parsers.dex.oL var6 = var4.q(var3[var5]);
               if (var6 == null) {
                  return null;
               }

               var4 = var6;
            }

            String var9 = var3[var3.length - 1];
            var2 = this.q(var4, var9);
            if (this.io != null && var2 != null) {
               if (this.io.size() >= 10000) {
                  this.io.clear();
               }

               this.io.put(var1, var2);
            }

            return var2;
         }
      } else {
         return null;
      }
   }

   private bjt q(com.pnfsoftware.jeb.corei.parsers.dex.oL var1, String var2) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.oL var4 : var1.getChildren()) {
         com.pnfsoftware.jeb.corei.parsers.dex.CU var5 = var4.xK();
         if (var5 instanceof bjt) {
            String var6 = ((bjt)var5).getName(true);
            if (var6 != null && var2.startsWith(var6)) {
               if (var6.length() == var2.length()) {
                  return (bjt)var5;
               }

               bjt var7 = this.q(var4, var2);
               if (var7 != null) {
                  return var7;
               }
            }
         }
      }

      return null;
   }

   public int lm() {
      return this.JY;
   }
}
