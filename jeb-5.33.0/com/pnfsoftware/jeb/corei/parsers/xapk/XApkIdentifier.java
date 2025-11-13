package com.pnfsoftware.jeb.corei.parsers.xapk;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowser;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.ckz;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class XApkIdentifier extends AbstractUnitIdentifier {
   private static final ILogger pC = GlobalLog.getLogger(XApkIdentifier.class);

   public XApkIdentifier() {
      super("xapk", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("APK Extension file parser"), S.L("Android X-APK (extension) parser"), "PNF Software", Version.create(1, 0, 1));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (!(var1 instanceof FileInput)) {
         return false;
      } else if (!checkBytes(var1, 0, 80, 75, 3, 4)) {
         return false;
      } else {
         try {
            if (!new ckz.Av(var1, Sets.newHashSet("manifest.json"), null).kS()) {
               return false;
            }
         } catch (IOException var24) {
            pC.catchingSilent(var24);
            return false;
         }

         ZipBrowser var5 = null;

         boolean var10;
         try {
            File var6 = ((FileInput)var1).getFile();
            var5 = new ZipBrowser(var6);
            if (var5.getEntry("manifest.json") == null) {
               return false;
            }

            byte[] var25 = var5.readEntry("manifest.json");
            JSONParser var8 = new JSONParser();

            try {
               Object var9 = var8.parse(Strings.decodeUTF8(var25));
               var10 = ((Map)var9).get("xapk_version") != null;
            } catch (ParseException var21) {
               pC.catchingSilent(var21);
               return false;
            }
         } catch (IOException var22) {
            pC.catchingSilent(var22);
            return false;
         } finally {
            if (var5 != null) {
               try {
                  var5.close();
               } catch (IOException var20) {
               }
            }
         }

         return var10;
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Av(var1, var2, var3, var4, this.pdm);
   }
}
