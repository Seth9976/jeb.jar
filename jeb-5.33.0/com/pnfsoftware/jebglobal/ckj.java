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

public class ckj extends AbstractEnginesPlugin {
   private static final ILogger pC = GlobalLog.getLogger(ckj.class);
   private static final PluginInformation A = new PluginInformation(
      S.L("Generic Unpacker for APK"),
      S.L("Attempt to unpack protected APK and recover dynamically generated dex files."),
      "Nicolas Falliere",
      Version.create(0, 1),
      Version.create(5, 9)
   );
   private IEnginesContext kS;
   private IEventListener wS;

   @Override
   public IPluginInformation getPluginInformation() {
      return A;
   }

   private boolean pC() {
      return true;
   }

   @Override
   public void load(IEnginesContext var1) {
      this.kS = var1;
      this.wS = new ckk(this);
      var1.addListener(this.wS);
   }

   @Override
   public void dispose() {
      if (this.wS != null) {
         this.kS.removeListener(this.wS);
         this.wS = null;
      }
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      if (!this.pC()) {
         throw new RuntimeException(
            ckx.pC(
               new byte[]{
                  23,
                  7,
                  25,
                  10,
                  82,
                  25,
                  11,
                  29,
                  19,
                  73,
                  70,
                  67,
                  74,
                  65,
                  95,
                  87,
                  86,
                  71,
                  12,
                  66,
                  87,
                  16,
                  91,
                  91,
                  95,
                  84,
                  46,
                  28,
                  21,
                  10,
                  13,
                  17,
                  69,
                  5,
                  78,
                  19,
                  70,
                  27,
                  26,
                  0,
                  29,
                  28,
                  26,
                  83,
                  65,
                  10,
                  15,
                  10,
                  9,
                  7,
                  18,
                  17,
                  69,
                  7,
                  87,
                  80,
                  36,
                  77
               },
               2,
               33
            )
         );
      } else {
         pC.info(S.L("The generic unpacker can be run via the Android menu in the GUI."));
      }
   }
}
