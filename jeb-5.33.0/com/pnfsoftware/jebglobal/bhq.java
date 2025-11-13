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
public class bhq implements IDynamicContentManager {
   private static final ILogger pC = GlobalLog.getLogger(bhq.class);
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.vi A;
   @SerId(2)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws kS;

   public bhq(IDexDecompilerUnit var1) {
      this.A = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var1).ld();
      this.kS = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var1;
   }

   @Override
   public IDexType getDexType(String var1) {
      return this.A.ld().sY(var1);
   }

   @Override
   public boolean isAnonymousClass(IJavaType var1) {
      bfs var2 = this.A.WR().pC(var1.getSignature());
      return var2 != null && var2.isAnonymousClass();
   }

   @Override
   public boolean generateType(JavaOutputSink var1, IJavaType var2, boolean var3, boolean var4, long var5) {
      String var7 = var2.getSignature();
      bfy var8 = this.A.ld().sY(var7);
      if (var8 == null) {
         return false;
      } else {
         bfy var9 = var8;
         String var10 = var8.getSignature(true);
         if (var8.isArray()) {
            var9 = (bfy)this.A.ld().kS(var8.getNonArrayClass());
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
                  bfs var19 = var9.A();
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
         } else if (var11 instanceof bfs) {
            var1.appendAndRecord(var10, ItemClassIdentifiers.CLASS_NAME, ((bfs)var11).getItemId(), var4 ? 1 : 0);
         } else if (var11 instanceof bfy) {
            var1.appendAndRecord(var10, ItemClassIdentifiers.EXTERNAL_CLASS_NAME, ((bfy)var11).getItemId());
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
      bfy var4 = this.A.ld().sY(var3);
      if (var4 == null) {
         return null;
      } else {
         if (var4.isArray()) {
            bfy var5 = (bfy)this.A.ld().kS(var4.getNonArrayClass());
            if (var5 == null) {
               return null;
            }

            var4 = var5;
         }

         var3 = var4.getSignature(true);
         String var10 = JavaTypeUtil.generatePackageNameStandardRepresentation(var3);
         com.pnfsoftware.jeb.corei.parsers.dex.qt var6 = this.A.ld().A(var10);
         long var7 = var6 == null ? 0L : var6.getItemId();
         var1.appendAndRecord(var10, ItemClassIdentifiers.PACKAGE_NAME, var7);
         return var10;
      }
   }

   @Override
   public String getComment(ICodeCoordinates var1) {
      String var2 = this.A.getCommentManager().formatComments2(var1, false, 0, 1);
      return var2 != null && !var2.isEmpty() ? var2 : null;
   }

   @Override
   public String getPreComment(ICodeCoordinates var1) {
      return this.A.getCommentManager().getPrimary2(var1, -1);
   }

   @Override
   public String getMethodSignature(int var1) {
      return this.A.sY(var1).getSignature(true);
   }

   @Override
   public String getMethodName(int var1) {
      return this.A.sY(var1).getName(true);
   }

   @Override
   public boolean wasMethodRenamed(int var1) {
      return this.A.sY(var1).kS();
   }

   @Override
   public long getMethodId(int var1) {
      return this.A.sY(var1).getItemId();
   }

   @Override
   public long getBestVirtualMethodId(int var1, IJavaType var2, List var3) {
      bfu var4 = this.A.sY(var1);
      bhc var5 = new bhc(this.A);
      if (var2 != null) {
         List var6 = var5.pC(var4, true);
         bfy var7 = this.A.ld().sY(var2.getSignature());
         if (var7 != null) {
            boolean var8 = false;

            for (int var10 : var6) {
               bfu var11 = this.A.sY(var10);
               if (var11.getClassTypeIndex() == var7.getIndex()) {
                  var8 = true;
               }

               if (var8 && var11.A() != null) {
                  return var11.getItemId();
               }
            }
         } else {
            var2 = null;
         }
      }

      if (var2 == null) {
         List var12 = var5.kS(var4, true);
         int var13 = -1;

         for (int var15 : var12) {
            if (this.A.sY(var15).isInternal()) {
               if (var13 >= 0) {
                  var13 = -1;
                  break;
               }

               var13 = var15;
            }
         }

         if (var13 >= 0) {
            return this.A.sY(var13).getItemId();
         }

         if (var3 != null) {
            var3.addAll(var12);
         }

         if (var12.size() == 1) {
            return this.A.sY((Integer)var12.get(0)).getItemId();
         }
      }

      return var4.getItemId();
   }

   @Override
   public long getImplStaticMethodId(int var1) {
      bfu var2 = this.A.sY(var1);
      if (var2.isInternal()) {
         return var2.getItemId();
      } else {
         bhc var3 = new bhc(this.A);

         for (int var6 : var3.pC(var2, true)) {
            if (this.A.sY(var6).isInternal()) {
               return this.A.sY(var6).getItemId();
            }
         }

         return var2.getItemId();
      }
   }

   @Override
   public String getFieldSignature(int var1) {
      return this.A.E(var1).getSignature(true);
   }

   @Override
   public boolean wasFieldRenamed(int var1) {
      return this.A.E(var1).kS();
   }

   @Override
   public String getFieldName(int var1) {
      return this.A.E(var1).getName(true);
   }

   @Override
   public long getFieldId(int var1) {
      return this.A.E(var1).getItemId();
   }

   @Override
   public long getImplFieldId(int var1) {
      bft var2 = this.A.E(var1);
      if (var2.isInternal()) {
         return var2.getItemId();
      } else {
         bhb var3 = new bhb(this.A);
         Integer var4 = var3.pC(var2);
         return var4 != null ? this.A.E(var4).getItemId() : var2.getItemId();
      }
   }

   @Override
   public String getLabelName(ICodeCoordinates var1) {
      return this.A.pC(var1);
   }

   @Override
   public long getLabelId(ICodeCoordinates var1) {
      return this.A.pC((Object)var1);
   }

   @Override
   public String getIdentifierName(IdentifierCoordinates var1) {
      return this.A.pC(var1);
   }

   @Override
   public long getIdentifierId(IdentifierCoordinates var1) {
      return this.A.pC((Object)var1);
   }

   @Override
   public StringInfo getStringInfo(String var1) {
      bfx var2 = (bfx)this.A.sY().kS(var1);
      if (var2 == null) {
         return null;
      } else {
         int var3 = this.A.A(var2);
         return new StringInfo(this.A.pC(var2), var2.getValue(), var2.isArtificial(), var3);
      }
   }

   @Override
   public long getImmediateId(long var1) {
      return this.A.pC(Long.valueOf(var1));
   }

   @Override
   public int getImmediatePreferredBase(long var1) {
      return this.A.kS(var1);
   }

   @Override
   public String retrieveImmediateHint(long var1) {
      if (this.A.getParent() instanceof com.pnfsoftware.jeb.corei.parsers.apk.Ws && (var1 & -4294967296L) == 0L) {
         com.pnfsoftware.jeb.corei.parsers.apk.Ws var3 = (com.pnfsoftware.jeb.corei.parsers.apk.Ws)this.A.getParent();
         return var3.pC().generateAutoCommentForPotentialResourceId((int)var1);
      } else {
         return null;
      }
   }

   @Override
   public List findTypesWithSuperMethods(int var1) {
      if (this.kS == null) {
         return Collections.emptyList();
      } else {
         bfu var2 = this.A.sY(var1);
         bgd var3 = var2.A();
         Assert.a(var3 != null);
         bgl var4 = this.kS.fI();
         List var5 = var4.findTypesWithSuperMethods(var2.getClassTypeSignature(true), var2.getName(), var2.E(), true);
         ArrayList var6 = new ArrayList();

         for (String var8 : var5) {
            var6.add(bak.A(var8));
         }

         return var6;
      }
   }

   @Override
   public String getDecryptorData(int var1) {
      ccw var2 = ccw.pC(this.kS.gp(), bxp.A);
      ccw.Av var3 = var2.pC(this.A.sY(var1).getSignature(false));
      if (var3 == null) {
         return null;
      } else {
         int var4 = var3.A();
         if (var4 == 0) {
            return null;
         } else {
            StringBuilder var5 = new StringBuilder();
            int var6 = var3.kS();
            Strings.ff(var5, S.L("String Decryptor: %d succeeded, %d failed"), var4, var6);
            if (var6 > 0) {
               Map var7 = var3.pC();

               for (ICodeCoordinates var9 : var7.keySet()) {
                  String var10 = this.A.getAddressFromCodeCoordinates(var9);
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
      com.pnfsoftware.jeb.corei.parsers.dex.Sv var3 = this.A.WR(var1);
      return var3 != null ? this.kS.pC(var3, var2) : false;
   }
}
