package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.DexParsingException;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForParameter;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationsDirectory;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Ser
public class bfs extends com.pnfsoftware.jeb.corei.parsers.dex.Sv implements IDexClass, bgc {
   private static final ILogger oT = GlobalLog.getLogger(bfs.class);
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   int UT;
   @SerId(6)
   int[] E;
   @SerId(7)
   int sY;
   @SerId(8)
   bec ys;
   @SerId(9)
   bfp ld;
   @SerId(10)
   beg[] gp;
   @SerId(11)
   private boolean fI = false;
   @SerId(12)
   private boolean WR = false;
   @SerId(13)
   private int NS = -1;
   @SerId(14)
   private int vP = -1;
   @SerId(15)
   private int[] xC = null;
   @SerId(16)
   private String ED = "";
   @SerId(17)
   private String Sc = "";
   @SerId(18)
   private volatile String ah = null;
   @SerId(19)
   private volatile int eP;
   @SerId(20)
   private Integer UO;
   @SerId(21)
   private int Ab;
   @SerId(22)
   private boolean rl;
   @SerId(23)
   private Integer z;

   public bfs(
      com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2, int var3, int var4, int var5, int[] var6, int var7, bec var8, bfp var9, beg[] var10, int var11
   ) throws DexParsingException {
      super(var1);
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.E = var6 == null ? new int[0] : var6;
      this.sY = var7;
      this.ys = var8;
      this.ld = var9;
      this.gp = var10 == null ? new beg[0] : var10;
      this.z = var11;
      if (var8 != null) {
         this.gp();
      }

      String var12 = var1.pC(var3, false);
      int var13 = var12.lastIndexOf(47);
      if (var13 < 0) {
         var13 = 0;
      }

      String var14 = var12.substring(var13 + 1, var12.length() - 1);
      if (!this.fI) {
         this.ED = var14;
      } else {
         this.WR = this.oT();
         if (!this.WR) {
            oT.debug("%s: %s", S.L("Invalid inner class"), var12);
            this.ED = var14;
            this.Sc = "";
            this.fI = false;
         } else if (this.ED.isEmpty() && !this.Sc.isEmpty() && !Character.isDigit(this.Sc.charAt(0))) {
            this.ED = this.Sc;
         }
      }
   }

   private void gp() throws DexParsingException {
      String var1 = this.pC.pC(this.kS, false);

      for (IDexAnnotationItem var3 : this.ys.getClassAnnotations()) {
         if (var3.isSystemLevelAnnotation()) {
            IDexAnnotation var4 = var3.getAnnotation();
            String var5 = this.pC.pC(var4.getTypeIndex(), false);
            if (var5.equals("Ldalvik/annotation/InnerClass;")) {
               this.fI = true;
               boolean var24 = false;

               for (IDexAnnotationElement var32 : var4.getElements()) {
                  String var36 = this.pC.A(var32.getNameIndex());
                  if (var36.equals("name")) {
                     if (!var32.getValue().isNull() && var32.getValue().getType() == 23) {
                        this.ED = this.pC.A(var32.getValue().getStringIndex());
                     } else {
                        this.ED = "";
                     }

                     var24 = true;
                  } else if (var36.equals("accessFlags") && var32.getValue().getType() == 4) {
                     this.Ab = var32.getValue().getInt();
                  }
               }

               if (!var24) {
                  this.pC.logWarn(true, S.L("%s: InnerClass annotation should have a 'name' element"), var1);
                  this.ED = "";
               }
            } else if (var5.equals("Ldalvik/annotation/EnclosingClass;")) {
               boolean var23 = false;

               for (IDexAnnotationElement var31 : var4.getElements()) {
                  String var35 = this.pC.A(var31.getNameIndex());
                  if (var35.equals("value")) {
                     IDexValue var39 = var31.getValue();
                     if (var39.getType() == 24) {
                        this.NS = var31.getValue().getTypeIndex();
                     } else if (var39.getType() != 30) {
                        this.pC.logWarn(true, S.L("%s: EnclosingClass annotation value element does not specify a type!"), var1);
                     }

                     var23 = true;
                  }
               }

               if (!var23) {
                  this.pC.logWarn(true, S.L("%s: EnclosingClass annotation should have a 'value' element"), var1);
               }
            } else if (var5.equals("Ldalvik/annotation/EnclosingMethod;")) {
               boolean var22 = false;

               for (IDexAnnotationElement var30 : var4.getElements()) {
                  String var34 = this.pC.A(var30.getNameIndex());
                  if (var34.equals("value")) {
                     IDexValue var38 = var30.getValue();
                     if (var38.getType() == 26) {
                        this.vP = var38.getMethodIndex();
                     } else if (var38.getType() == 23) {
                        this.UO = var38.getStringIndex();
                        S.L("EnclodingMethod does not reference a Method: %s");
                        Object[] var10000 = new Object[]{this.pC.kS(this.UO)};
                     } else if (var38.getType() != 30) {
                        String var41 = Strings.ff(S.L("%s: Unexpected value type in EnclosingMethod annotation: %d"), var1, var38.getType());
                        JebCoreService.notifySilentExceptionToClient(new RuntimeException(var41));
                     }

                     var22 = true;
                  }
               }

               if (!var22) {
                  this.pC.logWarn(true, S.L("%s: EnclosingMethod annotation should have a 'value' element"), var1);
               }
            } else if (var5.equals("Ldalvik/annotation/MemberClasses;")) {
               boolean var6 = false;

               for (IDexAnnotationElement var8 : var4.getElements()) {
                  String var9 = this.pC.A(var8.getNameIndex());
                  if (var9.equals("value")) {
                     if (var8.getValue().getType() == 28) {
                        List var10 = var8.getValue().getArray();
                        this.xC = new int[var10.size()];
                        int var11 = 0;

                        for (IDexValue var13 : var10) {
                           if (var13.getType() == 24) {
                              this.xC[var11++] = var13.getTypeIndex();
                           } else {
                              this.pC.logWarn(true, S.L("%s: MemberClasses annotation value must be an array of types!"), var1);
                           }
                        }
                     } else {
                        this.pC.logWarn(true, S.L("%s: MemberClasses annotation value element does not specify an array!"), var1);
                     }

                     var6 = true;
                  }
               }

               if (!var6) {
                  this.pC.logWarn(true, S.L("%s: MemberClasses annotation should have a 'value' element"), var1);
               }
            }
         }
      }

      if (this.fI) {
         if (this.NS < 0 && this.vP < 0 && this.UO == null) {
            this.pC.logWarn(true, S.L("%s: Inner class has neither an enclosing class nor an enclosing method/method-type"), var1);
         }

         if (this.NS >= 0 && this.vP >= 0) {
            this.pC.logWarn(true, Strings.ff(S.L("%s: Inner class has both an enclosing class and an enclosing method"), var1));
         }
      }

      for (IDexAnnotationForMethod var19 : this.ys.getMethodsAnnotations()) {
         bfu var20 = this.pC.sY(var19.getMethodIndex());
         bgd var21 = var20.A();
         if (var21 == null) {
            this.pC.logWarn(true, S.L("%s: Annotation is on external method %s"), var1, var20.getSignature(false));
         } else {
            for (IDexAnnotationItem var29 : var19.getAnnotationItems()) {
               if (var29.isSystemLevelAnnotation()) {
                  IDexAnnotation var33 = var29.getAnnotation();
                  String var37 = this.pC.pC(var33.getTypeIndex(), false);
                  if (var37.equals("Ldalvik/annotation/Throws;")) {
                     int[] var40 = null;

                     for (IDexAnnotationElement var43 : var33.getElements()) {
                        String var44 = this.pC.A(var43.getNameIndex());
                        if (var44.equals("value")) {
                           if (var43.getValue().getType() == 28) {
                              List var14 = var43.getValue().getArray();
                              var40 = new int[var14.size()];
                              int var15 = 0;

                              for (IDexValue var17 : var14) {
                                 if (var17.getType() == 24) {
                                    var40[var15++] = var17.getTypeIndex();
                                 } else {
                                    this.pC.logWarn(true, S.L("%s: Throws annotation value must be an array of types!"), var1);
                                 }
                              }
                           } else {
                              this.pC.logWarn(true, S.L("%s: Throws annotation value element does not specify an array!"), var1);
                           }
                        }
                     }

                     if (var40 == null) {
                        this.pC.logWarn(true, S.L("%s: 'Throws' annotation on method %s should have a 'value' element"), var1, var20.getSignature(false));
                     } else {
                        var21.pC(var40);
                     }
                  }
               }
            }
         }
      }
   }

   private boolean oT() throws DexParsingException {
      String var1 = this.getSignature(false);
      if (!var1.endsWith(this.ED + ";")) {
         Object[] var15 = new Object[]{this.ED};
         return false;
      } else {
         int var2 = var1.length() - 1 - this.ED.length();

         int var3;
         for (var3 = var2 - 1; var3 >= 0; var3--) {
            char var4 = var1.charAt(var3);
            if (var4 == 'L' || var4 == '/') {
               return false;
            }

            if (var4 != '$') {
               Character.isDigit(var4);
            }

            if (var4 == '$') {
               break;
            }

            this.Sc = var4 + this.Sc;
         }

         if (var3 < 0) {
            Object[] var14 = new Object[0];
            return false;
         } else {
            boolean var9 = true;
            if (this.NS >= 0) {
               String var5 = this.pC.pC(this.NS, false);
               String var6 = var5.substring(0, var5.length() - 1);
               String var7 = var1.substring(0, var3);
               if (var6.equals(var7)) {
                  return true;
               }

               Object[] var10000 = new Object[]{var6, var7};
               var9 = false;
            }

            if (this.vP >= 0) {
               int var10 = this.pC.sY(this.vP).getClassTypeIndex();
               String var11 = this.pC.pC(var10, false);
               String var12 = var11.substring(0, var11.length() - 1);
               String var8 = var1.substring(0, var3);
               if (var8.startsWith(var12)) {
                  return true;
               }

               Object[] var13 = new Object[]{var12, var8};
               var9 = false;
            }

            return var9;
         }
      }
   }

   @Override
   public int getIndex() {
      return this.A;
   }

   @Override
   public boolean isInternal() {
      return true;
   }

   @Override
   public boolean isArtificial() {
      return this.A >= this.pC.WR().kS();
   }

   public boolean A() {
      return this.WR;
   }

   @Override
   public boolean isMemberClass() {
      return this.fI;
   }

   @Override
   public boolean isAnonymousClass() {
      return this.rl ? true : this.isMemberClass() && "".equals(this.ED);
   }

   public boolean pC(boolean var1) {
      if (!this.isMemberClass()) {
         return false;
      } else {
         this.fI = false;
         this.NS = -1;
         this.vP = -1;
         this.rl = false;
         this.pC.pC(var1, new UnitChangeEventData(8, this));
         return true;
      }
   }

   public boolean pC(int var1, boolean var2, boolean var3) {
      if (this.isMemberClass()) {
         return false;
      } else {
         this.fI = true;
         this.NS = var1;
         this.rl = var2;
         this.pC.pC(var3, new UnitChangeEventData(8, this));
         return true;
      }
   }

   public boolean A(int var1, boolean var2, boolean var3) {
      if (this.isMemberClass()) {
         return false;
      } else {
         this.fI = true;
         this.vP = var1;
         this.rl = var2;
         this.pC.pC(var3, new UnitChangeEventData(8, this));
         return true;
      }
   }

   @Override
   public boolean isNonStaticMemberClass() {
      return !this.isMemberClass() ? false : (this.Ab & 8) == 0;
   }

   @Override
   public boolean isStaticMemberClass() {
      return this.isMemberClass() && !this.isAnonymousClass() ? (this.Ab & 8) != 0 : false;
   }

   public int kS() {
      return this.NS;
   }

   public int wS() {
      return this.vP;
   }

   @Override
   public int getClassTypeIndex() {
      return this.kS;
   }

   public bfy UT() {
      return this.pC.wS(this.kS);
   }

   @Override
   public int getAccessFlags() {
      return this.wS;
   }

   @Override
   public boolean isTrueClass() {
      return (this.wS & 25088) == 0;
   }

   @Override
   public boolean isEnumeration() {
      return (this.wS & 16384) != 0;
   }

   @Override
   public boolean isInterface() {
      return (this.wS & 512) != 0;
   }

   @Override
   public boolean isAnnotation() {
      return (this.wS & 8192) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.wS & 4096) != 0;
   }

   @Override
   public String getSupertypeSignature(boolean var1) {
      return this.UT < 0 ? "Ljava/lang/Object;" : this.pC.pC(this.UT, var1);
   }

   @Override
   public int getSuperTypeIndex() {
      return this.UT;
   }

   @Override
   public List getSupertypes() {
      return Arrays.asList(this.pC.wS(this.UT));
   }

   @Override
   public String[] getInterfaceSignatures(boolean var1) {
      String[] var2 = new String[this.E.length];
      int var3 = 0;

      for (int var7 : this.E) {
         var2[var3] = this.pC.pC(var7, var1);
         var3++;
      }

      return var2;
   }

   @Override
   public int[] getInterfaceTypeIndexes() {
      return this.E;
   }

   @Override
   public List getImplementedInterfaces() {
      ArrayList var1 = new ArrayList(this.E.length);

      for (int var5 : this.E) {
         var1.add(this.pC.wS(var5));
      }

      return var1;
   }

   @Override
   public int getSourceStringIndex() {
      return this.sY;
   }

   public beg[] E() {
      return this.gp;
   }

   @Override
   public List getStaticInitializers() {
      return Arrays.asList(this.gp);
   }

   @Override
   public int getDexEntryIndex() {
      return this.z == null ? -1 : this.z;
   }

   public bfp sY() {
      return this.ld;
   }

   @Override
   public IDexAnnotationsDirectory getAnnotationsDirectory() {
      return this.ys == null ? bec.pC : this.ys;
   }

   public List ys() {
      return this.ys != null ? this.ys.getClassAnnotations() : Collections.emptyList();
   }

   public List pC(int var1) {
      if (this.ys != null) {
         for (IDexAnnotationForField var3 : this.ys.getFieldsAnnotations()) {
            int var4 = var3.getFieldIndex();
            if (var4 == var1) {
               return var3.getAnnotationItems();
            }

            if (var4 > var1) {
               break;
            }
         }
      }

      return Collections.emptyList();
   }

   public List A(int var1) {
      if (this.ys != null) {
         for (IDexAnnotationForMethod var3 : this.ys.getMethodsAnnotations()) {
            int var4 = var3.getMethodIndex();
            if (var4 == var1) {
               return var3.getAnnotationItems();
            }

            if (var4 > var1) {
               break;
            }
         }
      }

      return Collections.emptyList();
   }

   public List kS(int var1) {
      if (this.ys != null) {
         for (IDexAnnotationForParameter var3 : this.ys.getParametersAnnotations()) {
            int var4 = var3.getMethodIndex();
            if (var4 == var1) {
               return var3.getAnnotationItemSets();
            }

            if (var4 > var1) {
               break;
            }
         }
      }

      return Collections.emptyList();
   }

   @Override
   public String toString() {
      return Strings.ff("Class:#%d,name=%s,address=%s", this.A, this.getName(true), this.getAddress());
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return this.pC.pC(this.kS, var1, var2);
   }

   @Override
   public String getName(boolean var1) {
      return var1 && this.ah != null ? this.ah : this.ED;
   }

   @Override
   public String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public boolean setName(String var1) {
      return this.setName(var1, true);
   }

   @Override
   public boolean setName(String var1, boolean var2) {
      if (var1 == null || var1.isEmpty()) {
         var1 = this.ED;
      }

      if (var1.equals(this.getName(true))) {
         return true;
      } else {
         String var3 = this.getName();
         bfy var4 = (bfy)this.pC.ld().pC(this.kS);
         synchronized (var4) {
            if (!var4.pC(this.getName(true), var1)) {
               return false;
            }

            this.ah = var1;
         }

         this.pC.pC(var2, new UnitChangeEventData(1, this, var1, var3));
         return true;
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.qt ld() {
      return this.pC.ld().A(this);
   }

   @Override
   public int getGenericFlags() {
      int var1 = DexUtil.convertDexFlagsToCodeFlags(this.wS);
      if (this.isMemberClass()) {
         var1 |= 1048576;
      }

      if (this.isAnonymousClass()) {
         var1 |= 2097152;
      }

      if (this.isInternal()) {
         var1 |= Integer.MIN_VALUE;
      }

      if (this.isArtificial()) {
         var1 |= 1073741824;
      }

      return var1;
   }

   @Override
   public List getMethods() {
      if (this.ld == null) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(this.ld.wS());

         for (bgd var3 : this.ld.kS()) {
            var1.add(this.pC.sY(var3.getMethodIndex()));
         }

         return var1;
      }
   }

   public bfu pC(boolean var1, String var2, String... var3) {
      if (this.ld == null) {
         return null;
      } else {
         label37:
         for (IDexMethodData var5 : this.ld.kS()) {
            bfu var6 = this.pC.sY(var5.getMethodIndex());
            if (var6.getName(var1).equals(var2) && var6.sY() == var3.length) {
               int var7 = 0;

               for (IDexType var9 : var6.getParameterTypes()) {
                  if (!var9.getSignature(var1).equals(var3[var7])) {
                     continue label37;
                  }

                  var7++;
               }

               return var6;
            }
         }

         return null;
      }
   }

   public bfu pC(boolean var1, String var2, String var3) {
      if (this.ld == null) {
         return null;
      } else {
         for (IDexMethodData var5 : this.ld.kS()) {
            bfu var6 = this.pC.sY(var5.getMethodIndex());
            if (var6.getName(var1).equals(var2) && var6.UT().generate(var1).equals(var3)) {
               return var6;
            }
         }

         return null;
      }
   }

   public bft A(boolean var1, String var2, String var3) {
      if (this.ld == null) {
         return null;
      } else {
         for (IDexFieldData var5 : this.ld.pC()) {
            bft var6 = this.pC.E(var5.getFieldIndex());
            if (var6.getName(var1).equals(var2) && var6.getFieldTypeSignature(var1).equals(var3)) {
               return var6;
            }
         }

         return null;
      }
   }

   @Override
   public List getFields() {
      if (this.ld == null) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(this.ld.A());

         for (bfz var3 : this.ld.pC()) {
            var1.add(this.pC.E(var3.getFieldIndex()));
         }

         return var1;
      }
   }

   @Override
   public List getFieldsAndMethods() {
      if (this.ld == null) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(this.ld.A() + this.ld.wS());

         for (bfz var3 : this.ld.pC()) {
            var1.add(this.pC.E(var3.getFieldIndex()));
         }

         for (bgd var5 : this.ld.kS()) {
            var1.add(this.pC.sY(var5.getMethodIndex()));
         }

         return var1;
      }
   }

   public boolean pC(IDexField var1) {
      for (IDexField var3 : this.getFields()) {
         if (var1 == var3) {
            return true;
         }
      }

      return false;
   }

   @Override
   public Map getAnnotationDefaults() {
      if (!this.isAnnotation()) {
         return null;
      } else {
         bfy var1 = this.pC.A("Ldalvik/annotation/AnnotationDefault;");
         if (var1 == null) {
            return Collections.emptyMap();
         } else {
            int var2 = var1.getIndex();
            IDexAnnotation var3 = null;

            for (IDexAnnotationItem var5 : this.getAnnotationsDirectory().getClassAnnotations()) {
               if (var5.getVisibility() == 2 && var5.getAnnotation().getTypeIndex() == var2) {
                  var3 = var5.getAnnotation();
                  break;
               }
            }

            if (var3 == null) {
               return Collections.emptyMap();
            } else {
               IDexValue var9 = null;

               for (IDexAnnotationElement var6 : var3.getElements()) {
                  if ("value".equals(var6.getName(this.pC))) {
                     var9 = var6.getValue();
                     break;
                  }
               }

               if (var9 != null && var9.getType() == 29) {
                  IDexAnnotation var11 = var9.getAnnotation();
                  if (var11.getTypeIndex() != this.kS) {
                     return Collections.emptyMap();
                  } else {
                     LinkedHashMap var12 = new LinkedHashMap();

                     for (IDexAnnotationElement var8 : var11.getElements()) {
                        var12.put(var8.getName(this.pC), var8.getValue());
                     }

                     return var12;
                  }
               } else {
                  return Collections.emptyMap();
               }
            }
         }
      }
   }
}
