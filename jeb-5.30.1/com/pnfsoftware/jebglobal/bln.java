package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.units.code.StringInfo;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Ser
public class bln implements IDynamicContentManager {
   private static final ILogger q = GlobalLog.getLogger(bln.class);
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.bK RF;
   @SerId(2)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.ej xK;

   public bln(IDexDecompilerUnit var1) {
      this.RF = ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var1).TX();
      this.xK = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var1;
   }

   @Override
   public IDexType getDexType(String var1) {
      return this.RF.io().gO(var1);
   }

   @Override
   public boolean isAnonymousClass(IJavaType var1) {
      bjn var2 = this.RF.oQ().q(var1.getSignature());
      return var2 != null && var2.isAnonymousClass();
   }

   @Override
   public boolean generateType(JavaOutputSink var1, IJavaType var2, boolean var3, boolean var4, long var5) {
      String var7 = var2.getSignature();
      bjt var8 = this.RF.io().gO(var7);
      if (var8 == null) {
         return false;
      } else {
         bjt var9 = var8;
         String var10 = var8.getSignature(true);
         if (var8.isArray()) {
            var9 = (bjt)this.RF.io().xK(var8.getNonArrayClass());
            if (var9 == null) {
               throw new RuntimeException();
            }

            var10 = var9.getSignature(true);
         }

         Object var11 = null;
         if (var9.isClass()) {
            IJavaClass var12 = var1.getTopLevelClass();
            boolean var13 = true;
            if (var12 != null) {
               if (var9.isInternal()) {
                  bjn var19 = var9.xK();
                  if (var19 == null) {
                     throw new RuntimeException();
                  }

                  var11 = var19;
                  if (var19.isAnonymousClass()) {
                     var13 = true;
                  } else {
                     String var20 = var19.getName(true);
                     String var21 = var12.getImport(var20);
                     if (var21 == null || var21.equals(var10)) {
                        var12.addImport(var20, var10);
                        if (!var3) {
                           var10 = var20;
                           var13 = false;
                        }
                     }
                  }
               } else {
                  var11 = var9;
                  int var15 = var10.lastIndexOf(47);
                  String var14;
                  if (var15 >= 0) {
                     var14 = var10.substring(var15 + 1, var10.length() - 1);
                  } else {
                     var14 = var10.substring(1, var10.length() - 1);
                  }

                  String var16 = var12.getImport(var14);
                  if (var16 == null || var16.equals(var10)) {
                     var12.addImport(var14, var10);
                     if (!var3) {
                        var10 = var14;
                        var13 = false;
                     }
                  }
               }
            }

            if (var13) {
               var10 = var10.substring(1, var10.length() - 1).replace('/', '.');
            }
         }

         var10 = var10.replace('$', '.');
         if (var5 != 0L) {
            var1.appendAndRecord(var10, ItemClassIdentifiers.METHOD_NAME, var5);
         } else if (var11 instanceof bjn) {
            var1.appendAndRecord(var10, ItemClassIdentifiers.CLASS_NAME, ((bjn)var11).getItemId(), var4 ? 1 : 0);
         } else if (var11 instanceof bjt) {
            var1.appendAndRecord(var10, ItemClassIdentifiers.EXTERNAL_CLASS_NAME, ((bjt)var11).getItemId());
         } else {
            var1.appendAndRecord(var10, ItemClassIdentifiers.EXTERNAL_CLASS_NAME);
         }

         if (var8.isArray()) {
            for (int var18 = 0; var18 < var8.getDimensions(); var18++) {
               var1.append("[]");
            }
         }

         return true;
      }
   }

   @Override
   public String generatePackageName(JavaOutputSink var1, IJavaType var2) {
      String var3 = var2.getSignature();
      bjt var4 = this.RF.io().gO(var3);
      if (var4 == null) {
         return null;
      } else {
         if (var4.isArray()) {
            bjt var5 = (bjt)this.RF.io().xK(var4.getNonArrayClass());
            if (var5 == null) {
               return null;
            }

            var4 = var5;
         }

         var3 = var4.getSignature(true);
         String var10 = JavaTypeUtil.generatePackageNameStandardRepresentation(var3);
         com.pnfsoftware.jeb.corei.parsers.dex.Vj var6 = this.RF.io().RF(var10);
         long var7 = var6 == null ? 0L : var6.getItemId();
         var1.appendAndRecord(var10, ItemClassIdentifiers.PACKAGE_NAME, var7);
         return var10;
      }
   }

   @Override
   public String getComment(ICodeCoordinates var1) {
      String var2 = this.RF.getCommentManager().formatComments2(var1, false, 0, 1);
      return var2 != null && !var2.isEmpty() ? var2 : null;
   }

   @Override
   public String getPreComment(ICodeCoordinates var1) {
      return this.RF.getCommentManager().getPrimary2(var1, -1);
   }

   @Override
   public String getMethodSignature(int var1) {
      return this.RF.gO(var1).getSignature(true);
   }

   @Override
   public String getMethodName(int var1) {
      return this.RF.gO(var1).getName(true);
   }

   @Override
   public boolean wasMethodRenamed(int var1) {
      return this.RF.gO(var1).xK();
   }

   @Override
   public long getMethodId(int var1) {
      return this.RF.gO(var1).getItemId();
   }

   @Override
   public long getBestVirtualMethodId(int var1, IJavaType var2, List var3) {
      bjp var4 = this.RF.gO(var1);
      bky var5 = new bky(this.RF);
      if (var2 != null) {
         List var6 = var5.q(var4, true);
         bjt var7 = this.RF.io().gO(var2.getSignature());
         if (var7 != null) {
            boolean var8 = false;

            for (int var10 : var6) {
               bjp var11 = this.RF.gO(var10);
               if (var11.getClassTypeIndex() == var7.getIndex()) {
                  var8 = true;
               }

               if (var8 && var11.RF() != null) {
                  return var11.getItemId();
               }
            }
         } else {
            var2 = null;
         }
      }

      if (var2 == null) {
         List var12 = var5.xK(var4, true);
         int var13 = -1;

         for (int var15 : var12) {
            if (this.RF.gO(var15).isInternal()) {
               if (var13 >= 0) {
                  var13 = -1;
                  break;
               }

               var13 = var15;
            }
         }

         if (var13 >= 0) {
            return this.RF.gO(var13).getItemId();
         }

         if (var3 != null) {
            var3.addAll(var12);
         }

         if (var12.size() == 1) {
            return this.RF.gO((Integer)var12.get(0)).getItemId();
         }
      }

      return var4.getItemId();
   }

   @Override
   public long getImplStaticMethodId(int var1) {
      bjp var2 = this.RF.gO(var1);
      if (var2.isInternal()) {
         return var2.getItemId();
      } else {
         bky var3 = new bky(this.RF);

         for (int var6 : var3.q(var2, true)) {
            if (this.RF.gO(var6).isInternal()) {
               return this.RF.gO(var6).getItemId();
            }
         }

         return var2.getItemId();
      }
   }

   @Override
   public String getFieldSignature(int var1) {
      return this.RF.oW(var1).getSignature(true);
   }

   @Override
   public boolean wasFieldRenamed(int var1) {
      return this.RF.oW(var1).xK();
   }

   @Override
   public String getFieldName(int var1) {
      return this.RF.oW(var1).getName(true);
   }

   @Override
   public long getFieldId(int var1) {
      return this.RF.oW(var1).getItemId();
   }

   @Override
   public long getImplFieldId(int var1) {
      bjo var2 = this.RF.oW(var1);
      if (var2.isInternal()) {
         return var2.getItemId();
      } else {
         bkx var3 = new bkx(this.RF);
         Integer var4 = var3.q(var2);
         return var4 != null ? this.RF.oW(var4).getItemId() : var2.getItemId();
      }
   }

   @Override
   public String getLabelName(ICodeCoordinates var1) {
      return this.RF.RF(var1);
   }

   @Override
   public long getLabelId(ICodeCoordinates var1) {
      return this.RF.q((Object)var1);
   }

   @Override
   public String getIdentifierName(IdentifierCoordinates var1) {
      return this.RF.q(var1);
   }

   @Override
   public long getIdentifierId(IdentifierCoordinates var1) {
      return this.RF.q((Object)var1);
   }

   @Override
   public StringInfo getStringInfo(String var1) {
      bjs var2 = (bjs)this.RF.JY().xK(var1);
      if (var2 == null) {
         return null;
      } else {
         int var3 = this.RF.RF(var2);
         return new StringInfo(this.RF.q(var2), var2.getValue(), var2.isArtificial(), var3);
      }
   }

   @Override
   public long getImmediateId(long var1) {
      return this.RF.q(Long.valueOf(var1));
   }

   @Override
   public int getImmediatePreferredBase(long var1) {
      return this.RF.Uv(var1);
   }

   @Override
   public String retrieveImmediateHint(long var1) {
      if (this.RF.getParent() instanceof com.pnfsoftware.jeb.corei.parsers.apk.ej && (var1 & -4294967296L) == 0L) {
         com.pnfsoftware.jeb.corei.parsers.apk.ej var3 = (com.pnfsoftware.jeb.corei.parsers.apk.ej)this.RF.getParent();
         return var3.q().generateAutoCommentForPotentialResourceId((int)var1);
      } else {
         return null;
      }
   }

   @Override
   public List findTypesWithSuperMethods(int var1) {
      if (this.xK == null) {
         return Collections.emptyList();
      } else {
         bjp var2 = this.RF.gO(var1);
         bjy var3 = var2.RF();
         Assert.a(var3 != null);
         bkg var4 = this.xK.Xo();
         List var5 = var4.findTypesWithSuperMethods(var2.getClassTypeSignature(true), var2.getName(), var2.oW(), true);
         ArrayList var6 = new ArrayList();

         for (String var8 : var5) {
            var6.add(bed.xK(var8));
         }

         return var6;
      }
   }

   @Override
   public String getDecryptorData(int var1) {
      chs var2 = chs.q(this.xK.Rr(), cce.RF);
      chs.eo var3 = var2.q(this.RF.gO(var1).getSignature(false));
      if (var3 == null) {
         return null;
      } else {
         int var4 = var3.Dw();
         if (var4 == 0) {
            return null;
         } else {
            StringBuilder var5 = new StringBuilder();
            int var6 = var3.Uv();
            Strings.ff(var5, S.L("String Decryptor: %d succeeded, %d failed"), var4, var6);
            if (var6 > 0) {
               Map var7 = var3.xK();

               for (ICodeCoordinates var9 : var7.keySet()) {
                  String var10 = this.RF.getAddressFromCodeCoordinates(var9);
                  String var11 = (String)var7.get(var9);
                  if (var11 != null) {
                     Strings.ff(var5, S.L("\n- Failure: %s @ %s"), var11, var10);
                  }
               }
            }

            return var5.toString();
         }
      }
   }

   @Override
   public boolean isCollapsed(String var1, String[] var2) {
      com.pnfsoftware.jeb.corei.parsers.dex.CU var3 = this.RF.HF(var1);
      return var3 != null ? this.xK.q(var3, var2) : false;
   }
}
