package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Map;

public class cux extends AbstractEnginesPlugin {
   private static final ILogger q = GlobalLog.getLogger(cux.class);
   private static final PluginInformation RF = new PluginInformation(
      S.L("Generic Unpacker for APK"),
      S.L("Attempt to unpack protected APK and recover dynamically generated dex files."),
      "Nicolas Falliere",
      Version.create(0, 1),
      Version.create(5, 9)
   );
   private IEnginesContext xK;
   private IEventListener Dw;

   @Override
   public IPluginInformation getPluginInformation() {
      return RF;
   }

   private boolean q() {
      return true;
   }

   @Override
   public void load(IEnginesContext var1) {
      this.xK = var1;
      this.Dw = new cuy(this);
      var1.addListener(this.Dw);
   }

   @Override
   public void dispose() {
      if (this.Dw != null) {
         this.xK.removeListener(this.Dw);
         this.Dw = null;
      }
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      if (!this.q()) {
         throw new RuntimeException(
            cvm.q(
               new byte[]{
                  -12,
                  60,
                  1,
                  26,
                  83,
                  80,
                  28,
                  25,
                  18,
                  14,
                  7,
                  78,
                  67,
                  2,
                  15,
                  0,
                  1,
                  27,
                  84,
                  66,
                  7,
                  69,
                  73,
                  7,
                  29,
                  7,
                  21,
                  15,
                  26,
                  29,
                  8,
                  21,
                  17,
                  1,
                  68,
                  87,
                  30,
                  29,
                  28,
                  72,
                  84,
                  28,
                  1,
                  26,
                  83,
                  76,
                  5,
                  10,
                  6,
                  11,
                  29,
                  22,
                  69,
                  84,
                  13,
                  9,
                  21,
                  68
               },
               1,
               160
            )
         );
      } else {
         q.info(S.L("The generic unpacker can be run via the Android menu in the GUI."));
      }
   }
}
