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
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
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
public class bjn extends com.pnfsoftware.jeb.corei.parsers.dex.CU implements IDexClass, bjx {
   private static final ILogger lm = GlobalLog.getLogger(bjn.class);
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   int Uv;
   @SerId(6)
   int[] oW;
   @SerId(7)
   int gO;
   @SerId(8)
   bhw nf;
   @SerId(9)
   bjk gP;
   @SerId(10)
   bia[] za;
   @SerId(11)
   private boolean zz = false;
   @SerId(12)
   private boolean JY = false;
   @SerId(13)
   private int HF = -1;
   @SerId(14)
   private int LK = -1;
   @SerId(15)
   private int[] io = null;
   @SerId(16)
   private String qa = "";
   @SerId(17)
   private String Hk = "";
   @SerId(18)
   private volatile String Me = null;
   @SerId(19)
   private volatile int PV;
   @SerId(20)
   private Integer oQ;
   @SerId(21)
   private int xW;
   @SerId(22)
   private boolean KT;
   @SerId(23)
   private Integer Gf;

   public bjn(
      com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, int var3, int var4, int var5, int[] var6, int var7, bhw var8, bjk var9, bia[] var10, int var11
   ) throws DexParsingException {
      super(var1);
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
      this.oW = var6 == null ? new int[0] : var6;
      this.gO = var7;
      this.nf = var8;
      this.gP = var9;
      this.za = var10 == null ? new bia[0] : var10;
      this.Gf = var11;
      if (var8 != null) {
         this.JY();
      }

      String var12 = var1.q(var3, false);
      int var13 = var12.lastIndexOf(47);
      if (var13 < 0) {
         var13 = 0;
      }

      String var14 = var12.substring(var13 + 1, var12.length() - 1);
      if (!this.zz) {
         this.qa = var14;
      } else {
         this.JY = this.HF();
         if (!this.JY) {
            lm.debug("%s: %s", S.L("Invalid inner class"), var12);
            this.qa = var14;
            this.Hk = "";
            this.zz = false;
         } else if (this.qa.isEmpty() && !this.Hk.isEmpty() && !Character.isDigit(this.Hk.charAt(0))) {
            this.qa = this.Hk;
         }
      }
   }

   private void JY() throws DexParsingException {
      String var1 = this.q.q(this.xK, false);

      for (IDexAnnotationItem var3 : this.nf.getClassAnnotations()) {
         if (var3.isSystemLevelAnnotation()) {
            IDexAnnotation var4 = var3.getAnnotation();
            String var5 = this.q.q(var4.getTypeIndex(), false);
            if (var5.equals("Ldalvik/annotation/InnerClass;")) {
               this.zz = true;
               boolean var24 = false;

               for (IDexAnnotationElement var32 : var4.getElements()) {
                  String var36 = this.q.RF(var32.getNameIndex());
                  if (var36.equals("name")) {
                     if (!var32.getValue().isNull() && var32.getValue().getType() == 23) {
                        this.qa = this.q.RF(var32.getValue().getStringIndex());
                     } else {
                        this.qa = "";
                     }

                     var24 = true;
                  } else if (var36.equals("accessFlags") && var32.getValue().getType() == 4) {
                     this.xW = var32.getValue().getInt();
                  }
               }

               if (!var24) {
                  this.q.logWarn(true, S.L("%s: InnerClass annotation should have a 'name' element"), var1);
                  this.qa = "";
               }
            } else if (var5.equals("Ldalvik/annotation/EnclosingClass;")) {
               boolean var23 = false;

               for (IDexAnnotationElement var31 : var4.getElements()) {
                  String var35 = this.q.RF(var31.getNameIndex());
                  if (var35.equals("value")) {
                     IDexValue var39 = var31.getValue();
                     if (var39.getType() == 24) {
                        this.HF = var31.getValue().getTypeIndex();
                     } else if (var39.getType() != 30) {
                        this.q.logWarn(true, S.L("%s: EnclosingClass annotation value element does not specify a type!"), var1);
                     }

                     var23 = true;
                  }
               }

               if (!var23) {
                  this.q.logWarn(true, S.L("%s: EnclosingClass annotation should have a 'value' element"), var1);
               }
            } else if (var5.equals("Ldalvik/annotation/EnclosingMethod;")) {
               boolean var22 = false;

               for (IDexAnnotationElement var30 : var4.getElements()) {
                  String var34 = this.q.RF(var30.getNameIndex());
                  if (var34.equals("value")) {
                     IDexValue var38 = var30.getValue();
                     if (var38.getType() == 26) {
                        this.LK = var38.getMethodIndex();
                     } else if (var38.getType() == 23) {
                        this.oQ = var38.getStringIndex();
                        S.L("EnclodingMethod does not reference a Method: %s");
                        Object[] var10000 = new Object[]{this.q.xK(this.oQ)};
                     } else if (var38.getType() != 30) {
                        String var41 = Strings.ff(S.L("%s: Unexpected value type in EnclosingMethod annotation: %d"), var1, var38.getType());
                        JebCoreService.notifySilentExceptionToClient(new RuntimeException(var41));
                     }

                     var22 = true;
                  }
               }

               if (!var22) {
                  this.q.logWarn(true, S.L("%s: EnclosingMethod annotation should have a 'value' element"), var1);
               }
            } else if (var5.equals("Ldalvik/annotation/MemberClasses;")) {
               boolean var6 = false;

               for (IDexAnnotationElement var8 : var4.getElements()) {
                  String var9 = this.q.RF(var8.getNameIndex());
                  if (var9.equals("value")) {
                     if (var8.getValue().getType() == 28) {
                        List var10 = var8.getValue().getArray();
                        this.io = new int[var10.size()];
                        int var11 = 0;

                        for (IDexValue var13 : var10) {
                           if (var13.getType() == 24) {
                              this.io[var11++] = var13.getTypeIndex();
                           } else {
                              this.q.logWarn(true, S.L("%s: MemberClasses annotation value must be an array of types!"), var1);
                           }
                        }
                     } else {
                        this.q.logWarn(true, S.L("%s: MemberClasses annotation value element does not specify an array!"), var1);
                     }

                     var6 = true;
                  }
               }

               if (!var6) {
                  this.q.logWarn(true, S.L("%s: MemberClasses annotation should have a 'value' element"), var1);
               }
            }
         }
      }

      if (this.zz) {
         if (this.HF < 0 && this.LK < 0 && this.oQ == null) {
            this.q.logWarn(true, S.L("%s: Inner class has neither an enclosing class nor an enclosing method/method-type"), var1);
         }

         if (this.HF >= 0 && this.LK >= 0) {
            this.q.logWarn(true, Strings.ff(S.L("%s: Inner class has both an enclosing class and an enclosing method"), var1));
         }
      }

      for (IDexAnnotationForMethod var19 : this.nf.getMethodsAnnotations()) {
         bjp var20 = this.q.gO(var19.getMethodIndex());
         bjy var21 = var20.RF();
         if (var21 == null) {
            this.q.logWarn(true, S.L("%s: Annotation is on external method %s"), var1, var20.getSignature(false));
         } else {
            for (IDexAnnotationItem var29 : var19.getAnnotationItems()) {
               if (var29.isSystemLevelAnnotation()) {
                  IDexAnnotation var33 = var29.getAnnotation();
                  String var37 = this.q.q(var33.getTypeIndex(), false);
                  if (var37.equals("Ldalvik/annotation/Throws;")) {
                     int[] var40 = null;

                     for (IDexAnnotationElement var43 : var33.getElements()) {
                        String var44 = this.q.RF(var43.getNameIndex());
                        if (var44.equals("value")) {
                           if (var43.getValue().getType() == 28) {
                              List var14 = var43.getValue().getArray();
                              var40 = new int[var14.size()];
                              int var15 = 0;

                              for (IDexValue var17 : var14) {
                                 if (var17.getType() == 24) {
                                    var40[var15++] = var17.getTypeIndex();
                                 } else {
                                    this.q.logWarn(true, S.L("%s: Throws annotation value must be an array of types!"), var1);
                                 }
                              }
                           } else {
                              this.q.logWarn(true, S.L("%s: Throws annotation value element does not specify an array!"), var1);
                           }
                        }
                     }

                     if (var40 == null) {
                        this.q.logWarn(true, S.L("%s: 'Throws' annotation on method %s should have a 'value' element"), var1, var20.getSignature(false));
                     } else {
                        var21.q(var40);
                     }
                  }
               }
            }
         }
      }
   }

   private boolean HF() throws DexParsingException {
      String var1 = this.getSignature(false);
      if (!var1.endsWith(this.qa + ";")) {
         Object[] var15 = new Object[]{this.qa};
         return false;
      } else {
         int var2 = var1.length() - 1 - this.qa.length();

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

            this.Hk = var4 + this.Hk;
         }

         if (var3 < 0) {
            Object[] var14 = new Object[0];
            return false;
         } else {
            boolean var9 = true;
            if (this.HF >= 0) {
               String var5 = this.q.q(this.HF, false);
               String var6 = var5.substring(0, var5.length() - 1);
               String var7 = var1.substring(0, var3);
               if (var6.equals(var7)) {
                  return true;
               }

               Object[] var10000 = new Object[]{var6, var7};
               var9 = false;
            }

            if (this.LK >= 0) {
               int var10 = this.q.gO(this.LK).getClassTypeIndex();
               String var11 = this.q.q(var10, false);
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
      return this.RF;
   }

   @Override
   public boolean isInternal() {
      return true;
   }

   @Override
   public boolean isArtificial() {
      return this.RF >= this.q.oQ().xK();
   }

   public boolean RF() {
      return this.JY;
   }

   @Override
   public boolean isMemberClass() {
      return this.zz;
   }

   @Override
   public boolean isAnonymousClass() {
      return this.KT ? true : this.isMemberClass() && "".equals(this.qa);
   }

   public boolean q(boolean var1) {
      if (!this.isMemberClass()) {
         return false;
      } else {
         this.zz = false;
         this.HF = -1;
         this.LK = -1;
         this.KT = false;
         this.q.q(var1, new UnitChangeEventData(8, this));
         return true;
      }
   }

   public boolean q(int var1, boolean var2, boolean var3) {
      if (this.isMemberClass()) {
         return false;
      } else {
         this.zz = true;
         this.HF = var1;
         this.KT = var2;
         this.q.q(var3, new UnitChangeEventData(8, this));
         return true;
      }
   }

   public boolean RF(int var1, boolean var2, boolean var3) {
      if (this.isMemberClass()) {
         return false;
      } else {
         this.zz = true;
         this.LK = var1;
         this.KT = var2;
         this.q.q(var3, new UnitChangeEventData(8, this));
         return true;
      }
   }

   @Override
   public boolean isNonStaticMemberClass() {
      return !this.isMemberClass() ? false : (this.xW & 8) == 0;
   }

   @Override
   public boolean isStaticMemberClass() {
      return this.isMemberClass() && !this.isAnonymousClass() ? (this.xW & 8) != 0 : false;
   }

   public int xK() {
      return this.HF;
   }

   public int Dw() {
      return this.LK;
   }

   public String Uv() {
      return this.oQ == null ? null : this.q.RF(this.oQ.intValue());
   }

   public int[] oW() {
      return this.io == null ? ArrayUtil.NO_INT : this.io;
   }

   @Override
   public int getClassTypeIndex() {
      return this.xK;
   }

   public bjt gO() {
      return this.q.Dw(this.xK);
   }

   @Override
   public int getAccessFlags() {
      return this.Dw;
   }

   @Override
   public boolean isTrueClass() {
      return (this.Dw & 25088) == 0;
   }

   @Override
   public boolean isEnumeration() {
      return (this.Dw & 16384) != 0;
   }

   @Override
   public boolean isInterface() {
      return (this.Dw & 512) != 0;
   }

   @Override
   public boolean isAnnotation() {
      return (this.Dw & 8192) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.Dw & 4096) != 0;
   }

   @Override
   public String getSupertypeSignature(boolean var1) {
      return this.Uv < 0 ? "Ljava/lang/Object;" : this.q.q(this.Uv, var1);
   }

   @Override
   public int getSuperTypeIndex() {
      return this.Uv;
   }

   @Override
   public List getSupertypes() {
      return Arrays.asList(this.q.Dw(this.Uv));
   }

   @Override
   public String[] getInterfaceSignatures(boolean var1) {
      String[] var2 = new String[this.oW.length];
      int var3 = 0;

      for (int var7 : this.oW) {
         var2[var3] = this.q.q(var7, var1);
         var3++;
      }

      return var2;
   }

   @Override
   public int[] getInterfaceTypeIndexes() {
      return this.oW;
   }

   @Override
   public List getImplementedInterfaces() {
      ArrayList var1 = new ArrayList(this.oW.length);

      for (int var5 : this.oW) {
         var1.add(this.q.Dw(var5));
      }

      return var1;
   }

   @Override
   public int getSourceStringIndex() {
      return this.gO;
   }

   public bia[] nf() {
      return this.za;
   }

   @Override
   public List getStaticInitializers() {
      return Arrays.asList(this.za);
   }

   @Override
   public int getDexEntryIndex() {
      return this.Gf == null ? -1 : this.Gf;
   }

   public bjk gP() {
      return this.gP;
   }

   @Override
   public IDexAnnotationsDirectory getAnnotationsDirectory() {
      return this.nf == null ? bhw.q : this.nf;
   }

   public List za() {
      return this.nf != null ? this.nf.getClassAnnotations() : Collections.emptyList();
   }

   public List q(int var1) {
      if (this.nf != null) {
         for (IDexAnnotationForField var3 : this.nf.getFieldsAnnotations()) {
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

   public List RF(int var1) {
      if (this.nf != null) {
         for (IDexAnnotationForMethod var3 : this.nf.getMethodsAnnotations()) {
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

   public List xK(int var1) {
      if (this.nf != null) {
         for (IDexAnnotationForParameter var3 : this.nf.getParametersAnnotations()) {
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

   public int lm() {
      return this.PV;
   }

   public void Dw(int var1) {
      this.PV = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("Class:#%d,name=%s,address=%s", this.RF, this.getName(true), this.getAddress());
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return this.q.q(this.xK, var1, var2);
   }

   @Override
   public String getName(boolean var1) {
      return var1 && this.Me != null ? this.Me : this.qa;
   }

   @Override
   public String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public boolean setName(String var1) {
      return this.q(var1, true);
   }

   public boolean q(String var1, boolean var2) {
      if (var1 == null || var1.isEmpty()) {
         var1 = this.qa;
      }

      if (var1.equals(this.getName(true))) {
         return true;
      } else {
         String var3 = this.getName();
         bjt var4 = (bjt)this.q.io().q(this.xK);
         synchronized (var4) {
            if (!var4.q(this.getName(true), var1)) {
               return false;
            }

            this.Me = var1;
         }

         this.q.q(var2, new UnitChangeEventData(1, this, var1, var3));
         return true;
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Vj zz() {
      return this.q.io().RF(this);
   }

   @Override
   public int getGenericFlags() {
      int var1 = DexUtil.convertDexFlagsToCodeFlags(this.Dw);
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
      if (this.gP == null) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(this.gP.Dw());

         for (bjy var3 : this.gP.xK()) {
            var1.add(this.q.gO(var3.getMethodIndex()));
         }

         return var1;
      }
   }

   public bjp q(boolean var1, String var2, String... var3) {
      if (this.gP == null) {
         return null;
      } else {
         label37:
         for (IDexMethodData var5 : this.gP.xK()) {
            bjp var6 = this.q.gO(var5.getMethodIndex());
            if (var6.getName(var1).equals(var2) && var6.gO() == var3.length) {
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

   public bjp q(boolean var1, String var2, String var3) {
      if (this.gP == null) {
         return null;
      } else {
         for (IDexMethodData var5 : this.gP.xK()) {
            bjp var6 = this.q.gO(var5.getMethodIndex());
            if (var6.getName(var1).equals(var2) && var6.Uv().generate(var1).equals(var3)) {
               return var6;
            }
         }

         return null;
      }
   }

   public bjo RF(boolean var1, String var2, String var3) {
      if (this.gP == null) {
         return null;
      } else {
         for (IDexFieldData var5 : this.gP.q()) {
            bjo var6 = this.q.oW(var5.getFieldIndex());
            if (var6.getName(var1).equals(var2) && var6.getFieldTypeSignature(var1).equals(var3)) {
               return var6;
            }
         }

         return null;
      }
   }

   @Override
   public List getFields() {
      if (this.gP == null) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(this.gP.RF());

         for (bju var3 : this.gP.q()) {
            var1.add(this.q.oW(var3.getFieldIndex()));
         }

         return var1;
      }
   }

   public boolean q(IDexMethod var1) {
      for (IDexMethod var3 : this.getMethods()) {
         if (var1 == var3) {
            return true;
         }
      }

      return false;
   }

   public boolean q(IDexField var1) {
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
         bjt var1 = this.q.RF("Ldalvik/annotation/AnnotationDefault;");
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
                  if ("value".equals(var6.getName(this.q))) {
                     var9 = var6.getValue();
                     break;
                  }
               }

               if (var9 != null && var9.getType() == 29) {
                  IDexAnnotation var11 = var9.getAnnotation();
                  if (var11.getTypeIndex() != this.xK) {
                     return Collections.emptyMap();
                  } else {
                     LinkedHashMap var12 = new LinkedHashMap();

                     for (IDexAnnotationElement var8 : var11.getElements()) {
                        var12.put(var8.getName(this.q), var8.getValue());
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
