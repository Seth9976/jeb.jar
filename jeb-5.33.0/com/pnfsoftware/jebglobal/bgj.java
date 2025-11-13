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
public class bgj extends bgg {
   private static final ILogger sY = GlobalLog.getLogger(bgj.class, Integer.MAX_VALUE);
   @SerId(1)
   private Map ys = new HashMap();
   @SerId(2)
   private List ld = new ArrayList();
   @SerId(3)
   private Map gp = new HashMap();
   @SerId(4)
   private com.pnfsoftware.jeb.corei.parsers.dex.HE oT;
   @SerId(value = 5, deprecated = true)
   @Deprecated
   private boolean fI;
   @SerId(6)
   private int WR;
   @SerTransient
   private Set NS = new HashSet();
   @SerTransient
   private ConcurrentHashMap vP;
   private static final String[] xC = new String[]{
      "Ljava/lang/Object;", "Ljava/lang/String;", "Ljava/lang/Class;", "Ljava/lang/Enum;", "Ljava/lang/Throwable;", "Ljava/lang/Class;"
   };

   @SerCustomInitPostGraph
   private void gp() {
      this.vP = null;
      com.pnfsoftware.jeb.corei.parsers.dex.qt var1 = this.E();
      var1.A();
   }

   public bgj(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("types", var1, var2);
      this.oT = this.pC(null, "", false);
      this.gp();
      if (this.vP != null) {
         var1.addListener(new bgk(this, var1));
      }
   }

   private com.pnfsoftware.jeb.corei.parsers.dex.HE kS(com.pnfsoftware.jeb.corei.parsers.dex.Sv var1) {
      com.pnfsoftware.jeb.corei.parsers.dex.HE var2 = (com.pnfsoftware.jeb.corei.parsers.dex.HE)this.gp.get(var1);
      if (var2 == null) {
         var2 = new com.pnfsoftware.jeb.corei.parsers.dex.HE(var1);
         this.gp.put(var1, var2);
      }

      return var2;
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.HE pC(com.pnfsoftware.jeb.corei.parsers.dex.Sv var1) {
      return (com.pnfsoftware.jeb.corei.parsers.dex.HE)this.gp.get(var1);
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.HE pC(com.pnfsoftware.jeb.corei.parsers.dex.HE var1, String var2, boolean var3) {
      int var4 = this.ld.size();
      com.pnfsoftware.jeb.corei.parsers.dex.qt var5 = new com.pnfsoftware.jeb.corei.parsers.dex.qt(this.kS, var4, var2, var3);
      this.ld.add(var5);
      com.pnfsoftware.jeb.corei.parsers.dex.HE var6 = this.kS(var5);
      var6.pC(var1);
      var6.pC(!this.kS.A(var5));
      return var6;
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.HE pC(String var1) {
      return this.pC(var1, true, true);
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.HE pC(String var1, boolean var2) {
      return this.pC(var1, var2, true);
   }

   public synchronized com.pnfsoftware.jeb.corei.parsers.dex.HE pC(String var1, boolean var2, boolean var3) {
      ArrayList var4 = new ArrayList();
      if (!DexUtil.isClassname(var1, true, var4, this.kS.pC())) {
         return null;
      } else if (var4.isEmpty()) {
         return null;
      } else {
         com.pnfsoftware.jeb.corei.parsers.dex.HE var5 = this.oT;

         for (String var7 : var4) {
            com.pnfsoftware.jeb.corei.parsers.dex.HE var8 = var5.pC(var7);
            if (var8 == null) {
               var8 = this.pC(var5, var7, var2);
            }

            var5 = var8;
         }

         if (var3) {
            this.kS.kS(2, var1);
         }

         return var5;
      }
   }

   public List UT() {
      return Collections.unmodifiableList(this.ld);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.qt E() {
      return (com.pnfsoftware.jeb.corei.parsers.dex.qt)this.oT.kS();
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.qt A(int var1) {
      return (com.pnfsoftware.jeb.corei.parsers.dex.qt)this.ld.get(var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.qt A(String var1) {
      return this.A(var1, true);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.qt A(String var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else if (!var1.equals("") && !var1.equals("L/")) {
         ArrayList var3 = new ArrayList();
         if (!DexUtil.isClassname(var1, true, var3, this.kS.pC())) {
            var3.clear();
            if (var1.endsWith("/")) {
               var1 = var1.substring(0, var1.length() - 1) + ";";
            }

            if (!DexUtil.isInternalClassname(var1, true, var3, this.kS.pC())) {
               return null;
            }
         }

         if (var3.isEmpty()) {
            return null;
         } else {
            com.pnfsoftware.jeb.corei.parsers.dex.HE var4 = this.oT;

            for (String var6 : var3) {
               com.pnfsoftware.jeb.corei.parsers.dex.HE var7 = var4.pC(var6, var2);
               if (var7 == null) {
                  return null;
               }

               var4 = var7;
            }

            return var4 == null ? null : (com.pnfsoftware.jeb.corei.parsers.dex.qt)var4.kS();
         }
      } else {
         return this.E();
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.qt wS(String var1) {
      com.pnfsoftware.jeb.corei.parsers.dex.qt var2 = this.A(var1, false);
      if (var2 == null) {
         var2 = this.A(var1, true);
      }

      return var2;
   }

   public synchronized boolean pC(String var1, String var2, boolean var3) {
      com.pnfsoftware.jeb.corei.parsers.dex.qt var4 = this.A(var1);
      return var4 == null ? false : var4.pC(var2, var3, false);
   }

   public synchronized boolean pC(String var1, String var2, boolean var3, boolean var4) {
      com.pnfsoftware.jeb.corei.parsers.dex.qt var5 = this.A(var1);
      if (var5 == null) {
         return false;
      } else {
         com.pnfsoftware.jeb.corei.parsers.dex.qt var6 = this.A(var2);
         return var6 == null ? false : this.pC(var5, var6, 0, var3, var4);
      }
   }

   public synchronized boolean A(String var1, String var2, boolean var3, boolean var4) {
      bfs var5 = this.kS.gp(var1);
      if (var5 == null) {
         return false;
      } else {
         com.pnfsoftware.jeb.corei.parsers.dex.qt var6 = this.A(var2);
         return var6 == null ? false : this.pC(var5, var6, 0, var3, var4);
      }
   }

   com.pnfsoftware.jeb.corei.parsers.dex.qt A(com.pnfsoftware.jeb.corei.parsers.dex.Sv var1) {
      com.pnfsoftware.jeb.corei.parsers.dex.HE var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         for (com.pnfsoftware.jeb.corei.parsers.dex.HE var3 = var2.A(); var3 != null; var3 = var3.A()) {
            com.pnfsoftware.jeb.corei.parsers.dex.Sv var4 = var3.kS();
            if (var4 instanceof com.pnfsoftware.jeb.corei.parsers.dex.qt) {
               return (com.pnfsoftware.jeb.corei.parsers.dex.qt)var4;
            }
         }

         return null;
      }
   }

   com.pnfsoftware.jeb.corei.parsers.dex.HE pC(com.pnfsoftware.jeb.corei.parsers.dex.HE var1) {
      if (var1 == null) {
         return null;
      } else {
         for (com.pnfsoftware.jeb.corei.parsers.dex.HE var2 = var1.A(); var2 != null; var2 = var2.A()) {
            com.pnfsoftware.jeb.corei.parsers.dex.Sv var3 = var2.kS();
            if (var3 instanceof com.pnfsoftware.jeb.corei.parsers.dex.qt) {
               return var2;
            }
         }

         return null;
      }
   }

   public boolean pC(com.pnfsoftware.jeb.corei.parsers.dex.Sv var1, com.pnfsoftware.jeb.corei.parsers.dex.Sv var2, int var3, boolean var4, boolean var5) {
      if (var1 != null && var2 != null && var2 != var1) {
         com.pnfsoftware.jeb.corei.parsers.dex.HE var6 = this.pC(var1);
         if (var6 != null && var6.A() != null && var6.A().kS() != var2) {
            String var7 = var1.getName(true);

            for (com.pnfsoftware.jeb.corei.parsers.dex.HE var9 : this.pC(var2).getChildren()) {
               if (var9.kS().getName(true).equals(var7)) {
                  sY.warn(S.L("Can not move %s to %s: destination already exists"), var7, var2.getSignature(true));
                  return false;
               }
            }

            String var17 = var1.getSignature(true);
            String var18 = var2.getSignature(true);
            Object[] var10000 = new Object[]{var17, var18};
            if (!(var1 instanceof bfs) || !(var2 instanceof bfu) && !(var2 instanceof bfs)) {
               if (!(var1 instanceof bfs) && !(var1 instanceof com.pnfsoftware.jeb.corei.parsers.dex.qt)
                  || !(var2 instanceof com.pnfsoftware.jeb.corei.parsers.dex.qt)) {
                  return false;
               }

               if (var1 instanceof bfs var19) {
                  boolean var23 = false;
                  if (var19.isMemberClass()) {
                     var19.pC(var5);
                     var23 = true;
                  }

                  com.pnfsoftware.jeb.corei.parsers.dex.HE var27 = this.pC(var19);
                  com.pnfsoftware.jeb.corei.parsers.dex.HE var31 = this.pC(var2);
                  var27.pC(null);
                  var31.A(var27);

                  for (com.pnfsoftware.jeb.corei.parsers.dex.HE var37 : this.A(var27)) {
                     var37.pC(null);
                     var31.A(var37);
                  }

                  if (var23 && !var19.A()) {
                     bfy var36 = (bfy)this.pC(var19.getClassTypeIndex());
                     var36.pC(var36.getName(), var19.getName());
                  }
               } else {
                  com.pnfsoftware.jeb.corei.parsers.dex.qt var20 = (com.pnfsoftware.jeb.corei.parsers.dex.qt)var1;
                  com.pnfsoftware.jeb.corei.parsers.dex.HE var24 = this.pC(var20);
                  com.pnfsoftware.jeb.corei.parsers.dex.HE var28 = this.pC(var2);
                  var24.pC(null);
                  var28.A(var24);
               }
            } else {
               bfs var10 = (bfs)var1;
               if (var2 instanceof bfu) {
                  boolean var11 = (var3 & 1) != 0;
                  boolean var12 = (var3 & 2) != 0;
                  if (!var12 && !var11 && !this.pC(var10, (bfu)var2)) {
                     var12 = true;
                  }

                  if (var10.isMemberClass()) {
                     var10.pC(var5);
                  }

                  var10.A(var2.getIndex(), !var12, var5);
                  bfy var13 = (bfy)this.pC(var10.getClassTypeIndex());
                  bfy var14 = ((bfu)var2).wS();
                  var13.pC(var13.getName(), var14.getName() + "$1" + var10.getName());
               } else {
                  boolean var21 = (var3 & 1) != 0;
                  boolean var25 = (var3 & 2) != 0;
                  if (!var25 && !var21 && !this.pC(var10, (bfs)var2)) {
                     var25 = true;
                  }

                  if (var10.isMemberClass()) {
                     var10.pC(var5);
                  }

                  var10.pC(((bfs)var2).getClassTypeIndex(), !var25, var5);
                  bfy var29 = (bfy)this.pC(var10.getClassTypeIndex());
                  bfy var32 = (bfy)this.pC(((bfs)var2).getClassTypeIndex());
                  var29.pC(var29.getName(), var32.getName() + "$" + var10.getName());
               }

               com.pnfsoftware.jeb.corei.parsers.dex.HE var22 = this.pC(var10);
               com.pnfsoftware.jeb.corei.parsers.dex.HE var26 = this.pC(var2);
               var22.pC(null);
               var26.A(var22);
               com.pnfsoftware.jeb.corei.parsers.dex.HE var30 = this.pC(var26);

               for (com.pnfsoftware.jeb.corei.parsers.dex.HE var16 : this.A(var22)) {
                  var16.pC(null);
                  var30.A(var16);
               }
            }

            if (var4) {
               this.kS.pC(5, var17, var18, var3);
            }

            this.kS.pC(var5, new UnitChangeEventData(5, var2, var1));
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private List A(com.pnfsoftware.jeb.corei.parsers.dex.HE var1) {
      if (!(var1.kS() instanceof bfs)) {
         throw new IllegalArgumentException();
      } else {
         bfy var2 = (bfy)this.pC(((bfs)var1.kS()).getClassTypeIndex());
         com.pnfsoftware.jeb.corei.parsers.dex.HE var3 = this.pC(var2).A();
         ArrayList var4 = new ArrayList();
         HashSet var5 = new HashSet();
         this.pC(var1, var5);

         for (int var7 : var5) {
            bfy var8 = (bfy)this.pC(var7);
            com.pnfsoftware.jeb.corei.parsers.dex.HE var9 = this.pC(var8);
            if (var9.A() != var3) {
               throw new RuntimeException();
            }

            var4.add(var9);
         }

         return var4;
      }
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.dex.HE var1, Collection var2) {
      com.pnfsoftware.jeb.corei.parsers.dex.Sv var3 = var1.kS();
      if (var3 instanceof IDexClass) {
         int var4 = ((IDexClass)var3).getClassTypeIndex();
         var2.add(var4);
      }

      for (com.pnfsoftware.jeb.corei.parsers.dex.HE var5 : var1.getChildren()) {
         this.pC(var5, var2);
      }
   }

   public synchronized boolean pC(String var1, String var2, int var3, boolean var4, boolean var5) {
      return this.pC(this.kS.NS(var1), this.kS.NS(var2), var3, var4, var5);
   }

   boolean pC(bfs var1, bfu var2) {
      List var3 = (List)var1.getMethods().stream().filter(var0 -> var0.A().isConstructor()).collect(Collectors.toList());
      if (var3.size() != 1) {
         return false;
      } else {
         IDexMethod var4 = (IDexMethod)var3.get(0);
         Collection var5 = this.kS.getCrossReferences(DexPoolType.METHOD, var4.getIndex());
         if (var5.size() != 1) {
            return false;
         } else {
            ICodeCoordinates var6 = this.kS.A().pC(((IDexAddress)var5.iterator().next()).getInternalAddress());
            return var6 instanceof InstructionCoordinates && ((InstructionCoordinates)var6).getMethodId() == var2.getIndex();
         }
      }
   }

   boolean pC(bfs var1, bfs var2) {
      List var3 = (List)var1.getMethods().stream().filter(var0 -> var0.A().isConstructor()).collect(Collectors.toList());
      if (var3.size() != 1) {
         return false;
      } else {
         IDexMethod var4 = (IDexMethod)var3.get(0);
         Collection var5 = this.kS.getCrossReferences(DexPoolType.METHOD, var4.getIndex());
         if (var5.size() != 1) {
            return false;
         } else {
            ICodeCoordinates var6 = this.kS.A().pC(((IDexAddress)var5.iterator().next()).getInternalAddress());
            if (!(var6 instanceof InstructionCoordinates)) {
               return false;
            } else {
               int var7 = ((InstructionCoordinates)var6).getMethodId();
               bfu var8 = this.kS.sY(var7);
               return var8.A() != null && var8.A().isConstructor() ? var8.getClassTypeIndex() == var2.getClassTypeIndex() : false;
            }
         }
      }
   }

   public synchronized bfy UT(String var1) {
      bfy var2 = (bfy)this.kS(var1);
      if (var2 != null) {
         return var2;
      } else {
         if (var1.equals("Ljava/lang/invoke/StringConcatFactory;")
            || var1.equals("Ljava/lang/invoke/LambdaMetaFactory;")
            || var1.equals("Ljava/lang/runtime/SwitchBootstraps;")) {
            JebCoreService.notifySilentExceptionToClient(new RuntimeException("Making use of unsupported dynamic methods factory: " + var1), this.kS);
         }

         int var3 = this.UT.size();
         String var4 = null;
         if (this.NS == null) {
            this.NS = new HashSet();
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
               if (!this.ys.containsKey(var4)) {
                  this.ys.put(var4, new ArrayList());
                  this.NS.add(var4);
               }
            }

            var2 = new bfy(this, var3, var1);
         } catch (RuntimeException var8) {
            sY.catchingSilent(var8);
            String var6 = Formatter.escapeString(var1, false);
            String var7 = Strings.ff("L_bad_type_%d;", var3);
            this.kS.logError(true, S.L("Illegal type: \"%s\" -> replaced by \"%s\""), var6, var7);
            var2 = new bfy(this, var3, var7);
            this.WR++;
         }

         this.UT.add(var2);
         this.E.put(var1, var2);
         if (var4 != null) {
            ((List)this.ys.get(var4)).add(var2);
         }

         return var2;
      }
   }

   public synchronized void sY() {
      for (String var4 : xC) {
         if (this.kS(var4) == null) {
            this.kS.pC(var4, false);
            this.UT(var4);
         }
      }

      if (this.NS == null) {
         this.NS = new HashSet();
      }

      for (bfs var16 : this.kS.getClasses()) {
         bfy var24 = (bfy)this.UT.get(var16.getClassTypeIndex());
         if (var24.pC(var16)) {
            this.kS(var16);
            this.NS.add(var16.getSignature(false));
         }
      }

      for (String var17 : this.NS) {
         if (this.kS(var17) == null) {
            this.kS.pC(var17, false);
            this.UT(var17);
         }
      }

      for (String var18 : this.ys.keySet()) {
         bfy var25 = (bfy)this.E.get(var18);
         List var31 = (List)this.ys.get(var18);
         if (var25 != null && var31 != null) {
            for (bfy var6 : var31) {
               if (var6 != var25 && var6.getMasterType() == null) {
                  var25.pC(var6);
                  var6.A(var25);
               }
            }
         }
      }

      for (String var19 : this.NS) {
         String[] var26 = var19.substring(1, var19.length() - 1).split("/");
         com.pnfsoftware.jeb.corei.parsers.dex.HE var32 = this.oT;

         for (int var38 = 0; var38 < var26.length - 1; var38++) {
            String var41 = var26[var38];
            com.pnfsoftware.jeb.corei.parsers.dex.HE var7 = var32.pC(var41);
            if (var7 == null) {
               var7 = this.pC(var32, var41, false);
            }

            var32 = var7;
         }

         bfy var39 = (bfy)this.E.get(var19);
         var32.A(this.kS(var39));
         bfs var42 = var39.A();
         if (var42 != null) {
            var32.A(this.kS(var42));
         }
      }

      for (bft var20 : this.kS.getFields()) {
         bfy var27 = var20.wS();
         if (var27 == null) {
            throw new JebRuntimeException("Cannot retrieve type for field: " + var20);
         }

         bfs var33 = var27.A();
         if (var33 != null) {
            this.kS(var20).pC(this.pC(var33));
         }
      }

      for (bfu var21 : this.kS.getMethods()) {
         bfy var28 = var21.wS();
         if (var28 == null) {
            throw new JebRuntimeException("Cannot retrieve type for method: " + var21);
         }

         bfs var34 = var28.A();
         if (var34 != null) {
            this.kS(var21).pC(this.pC(var34));
         }
      }

      for (bfs var22 : this.kS.getClasses()) {
         if (var22.isMemberClass()) {
            int var29 = var22.kS();
            if (var29 >= 0) {
               bfs var35 = ((bfy)this.UT.get(var29)).A();
               if (var35 != null) {
                  this.pC(var22).pC(this.pC(var35));
               }
            } else {
               int var36 = var22.wS();
               if (var36 >= 0) {
                  this.pC(var22).pC(this.pC((com.pnfsoftware.jeb.corei.parsers.dex.Sv)this.kS.sY(var36)));
                  this.kS.sY(var36).getClassTypeIndex();
               }
            }
         }
      }

      this.NS.clear();
      int var15 = this.E("Ljava/lang/Enum;");
      if (var15 >= 0) {
         for (bfs var30 : this.kS.getClasses()) {
            if (var30.isEnumeration() && !var30.isMemberClass()) {
               int var37 = var30.getSuperTypeIndex();
               if (var37 != var15) {
                  bfy var40 = (bfy)this.pC(var37);
                  if (var40 != null) {
                     bfs var43 = var40.A();
                     if (var43 != null) {
                        if (!var43.isEnumeration() || var43.getSuperTypeIndex() != var15) {
                           RuntimeException var44 = new RuntimeException(
                              Strings.ff("Expected enum and custom constant for %s, %s", var30.getSignature(false), var43.getSignature(false))
                           );
                           JebCoreService.notifySilentExceptionToClient(var44);
                        } else if (this.pC(var30, var43, 0, true, false)) {
                           this.kS.logTrace("Moved custom enumerated constant %s under enum %s", var30.getSignature(false), var43.getSignature(false));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public String pC(int var1, boolean var2, boolean var3, boolean var4) {
      return var1 >= 0 && var1 < this.UT.size() ? ((bfy)this.UT.get(var1)).getSignature(var2, var3, var4) : null;
   }

   public String pC(int var1, boolean var2, boolean var3) {
      return this.pC(var1, var2, var3, true);
   }

   public int E(String var1) {
      bfy var2 = (bfy)this.E.get(var1);
      return var2 == null ? -1 : var2.getIndex();
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.HE ys() {
      return this.oT;
   }

   public bfy sY(String var1) {
      bfy var2 = (bfy)this.kS(var1);
      if (var2 != null) {
         return var2;
      } else if (var1.length() > 2 && var1.startsWith("L") && var1.endsWith(";")) {
         String[] var3 = var1.substring(1, var1.length() - 1).split("/");
         if (var3.length == 0) {
            return null;
         } else {
            if (this.vP != null) {
               var2 = (bfy)this.vP.get(var1);
               if (var2 != null) {
                  return var2;
               }
            }

            com.pnfsoftware.jeb.corei.parsers.dex.HE var4 = this.oT;

            for (int var5 = 0; var5 < var3.length - 1; var5++) {
               com.pnfsoftware.jeb.corei.parsers.dex.HE var6 = var4.pC(var3[var5]);
               if (var6 == null) {
                  return null;
               }

               var4 = var6;
            }

            String var9 = var3[var3.length - 1];
            var2 = this.pC(var4, var9);
            if (this.vP != null && var2 != null) {
               if (this.vP.size() >= 10000) {
                  this.vP.clear();
               }

               this.vP.put(var1, var2);
            }

            return var2;
         }
      } else {
         return null;
      }
   }

   private bfy pC(com.pnfsoftware.jeb.corei.parsers.dex.HE var1, String var2) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.HE var4 : var1.getChildren()) {
         com.pnfsoftware.jeb.corei.parsers.dex.Sv var5 = var4.kS();
         if (var5 instanceof bfy) {
            String var6 = ((bfy)var5).getName(true);
            if (var6 != null && var2.startsWith(var6)) {
               if (var6.length() == var2.length()) {
                  return (bfy)var5;
               }

               bfy var7 = this.pC(var4, var2);
               if (var7 != null) {
                  return var7;
               }
            }
         }
      }

      return null;
   }

   public int ld() {
      return this.WR;
   }
}
