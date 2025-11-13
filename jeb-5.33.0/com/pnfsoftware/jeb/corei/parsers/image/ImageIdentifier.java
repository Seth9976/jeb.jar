package com.pnfsoftware.jeb.corei.parsers.image;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.encoding.MimeType;
import java.util.Map;

public class ImageIdentifier extends AbstractUnitIdentifier {
   public ImageIdentifier() {
      super("image", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Image reader"), S.L("Image Reader"), "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         String var5 = MimeType.determineFromContent(var1);
         if (var5 == null) {
            return false;
         } else {
            String[] var6 = var5.split("/");
            if (var6.length != 2) {
               return false;
            } else {
               try {
                  return "image".equals(var6[0]);
               } catch (IllegalArgumentException var7) {
                  return false;
               }
            }
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Av(var1, var2, var3, var4, this.pdm);
   }
}
