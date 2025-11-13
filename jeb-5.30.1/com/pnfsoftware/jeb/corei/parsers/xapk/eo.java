package com.pnfsoftware.jeb.corei.parsers.xapk;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.IXApkUnit;
import com.pnfsoftware.jeb.corei.parsers.apk.ej;
import com.pnfsoftware.jeb.corei.parsers.json.nI;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowser;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Ser
public class eo extends AbstractBinaryUnit implements IArchiveUnit, IXApkUnit {
   private static final ILogger q = GlobalLog.getLogger(eo.class);
   @SerId(1)
   private Set RF = new HashSet();

   public eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/zip", var2, "xapk", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      if (!(this.getInput() instanceof FileInput)) {
         this.logWarn(false, S.L("Only top-level XAPK units can be handled by JEB at the moment."));
         return false;
      } else {
         ZipBrowser var1 = null;

         boolean var3;
         try {
            File var2 = ((FileInput)this.getInput()).getFile();
            var1 = new ZipBrowser(var2);
            HashSet var17 = new HashSet();
            if (var1.getEntry("manifest.json") != null) {
               var17.add("manifest.json");
               byte[] var4 = var1.readEntry("manifest.json");
               IUnit var5 = this.getUnitProcessor().process("Manifest", new BytesInput(var4), this, "json");
               var5.process();
               this.addChild(var5);
            }

            if (var1.getEntry("icon.png") != null) {
               var17.add("icon.png");
               byte[] var18 = var1.readEntry("icon.png");
               IUnit var20 = this.getUnitProcessor().process("Icon", new BytesInput(var18), this);
               var20.process();
               this.addChild(var20);
            }

            String var19 = this.getPackageName();
            if (var19 != null) {
               String var21 = var19 + ".apk";
               if (var1.getEntry(var21) != null) {
                  var17.add(var21);
                  byte[] var6 = var1.readEntry(var21);
                  IUnit var7 = this.getUnitProcessor().process(var19, new BytesInput(var6), this);
                  var7.process();
                  this.addChild(var7);
               }
            }

            for (String var24 : var1.getEntries().keySet()) {
               if (!var17.contains(var24)) {
                  this.RF.add(var24);
               }
            }

            String var23 = this.xK();
            if (!Strings.isBlank(var23)) {
               this.setName(var23);
            } else if (!Strings.isBlank(var19)) {
               this.setName(var19 + " (Extension)");
               return true;
            }

            return true;
         } catch (IOException var15) {
            q.catchingSilent(var15);
            var3 = false;
         } finally {
            if (var1 != null) {
               try {
                  var1.close();
               } catch (IOException var14) {
               }
            }
         }

         return var3;
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      if (!this.RF.isEmpty()) {
         var1.append(S.L("\nThe following entries were not processed:\n"));

         for (String var3 : this.RF) {
            Strings.ff(var1, "- %s\n", var3);
         }
      }

      return var1.toString();
   }

   public ej q() {
      return (ej)UnitUtil.findChild(this, null, ej.class, false, 0);
   }

   public nI RF() {
      return (nI)UnitUtil.findChild(this, null, nI.class, false, 0);
   }

   @Override
   public String getPackageName() {
      try {
         Object var1 = this.RF().getDocument();
         return ((Map)var1).get("package_name").toString();
      } catch (Exception var2) {
         return null;
      }
   }

   public String xK() {
      try {
         Object var1 = this.RF().getDocument();
         return ((Map)var1).get("name").toString();
      } catch (Exception var2) {
         return null;
      }
   }
}
