package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.OptionDefinition;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.IBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ckr extends AbstractEnginesPlugin {
   private static final ILogger pC = GlobalLog.getLogger(ckr.class);
   private IEnginesContext A;
   private IEventListener kS;
   private String wS;

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("VT Report Plugin"),
         S.L("Display and record VirusTotal reports for top-level binary units processed in JEB"),
         "PNF Software",
         Version.create(1, 0, 0)
      );
   }

   @Override
   public void load(IEnginesContext var1) {
      this.A = var1;
      this.wS = this.pC(var1);
      this.kS = new cks(this);
      var1.addListener(this.kS);
   }

   @Override
   public void dispose() {
      if (this.kS != null) {
         this.A.removeListener(this.kS);
         this.kS = null;
      }
   }

   @Override
   public List getExecutionOptionDefinitions() {
      return Arrays.asList(
         new OptionDefinition(null, S.L("Specify or update your VirusTotal API key:")), new OptionDefinition("apikey", this.wS, S.L("VirusTotal API Key"))
      );
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      if (var2 != null) {
         this.wS = (String)var2.get("apikey");
         if (this.wS != null) {
            this.pC(var1, this.wS);
         }
      }

      if (this.wS == null) {
         pC.error(S.L("In order to use the VirusTotal Scan Report plugin, set up your VT API key first!"));
      } else {
         this.A(var1);
      }
   }

   private String pC(IEnginesContext var1) {
      try {
         return var1.getPropertyManager().getString(".VirusTotalApiKey");
      } catch (Exception var2) {
         return null;
      }
   }

   private boolean pC(IEnginesContext var1, String var2) {
      try {
         var1.getPropertyManager().setString(".VirusTotalApiKey", var2);
         return true;
      } catch (Exception var3) {
         return false;
      }
   }

   private void A(IEnginesContext var1) {
      for (IRuntimeProject var3 : var1.getProjects()) {
         for (ILiveArtifact var5 : var3.getLiveArtifacts()) {
            for (IUnit var7 : var5.getUnits()) {
               if (var7 instanceof IBinaryUnit) {
                  try {
                     this.pC((IBinaryUnit)var7);
                  } catch (Exception var9) {
                     pC.catching(var9);
                  }
               }
            }
         }
      }
   }

   private boolean pC(IBinaryUnit var1) throws IOException, ParseException {
      if (Strings.isBlank(this.wS)) {
         return false;
      } else {
         String var2;
         try (InputStream var3 = var1.getInput().getStream()) {
            byte[] var4 = IO.readInputStream(var3);
            var2 = Formatter.byteArrayToHexString(Hash.calculateSHA256(var4)).toLowerCase();
         }

         pC.debug(S.L("Verifying SHA-256 hash on VirustTotal: %s"), var2);
         String var14 = Strings.ff("https://www.virustotal.com/vtapi/v2/file/report?apikey=%s&resource=%s", this.wS, var2);
         String var15 = this.A.getNetworkUtility().query(var14);
         pC.debug(var15);
         if (var15 != null && !var15.isEmpty()) {
            Map var5 = (Map)new JSONParser().parse(var15);
            Long var6 = (Long)var5.get("response_code");
            if (var6 == null) {
               pC.debug(S.L("Invalid VT answer: %s"), var14);
               return false;
            } else {
               if (var6 == 0L) {
                  UnitUtil.logInfo(var1, null, true, pC, S.L("VT: unknown file"));
               } else if (var6 == 1L) {
                  long var7 = (Long)var5.get("positives");
                  long var9 = (Long)var5.get("total");
                  String var11 = (String)var5.get("scan_date");
                  UnitUtil.logInfo(var1, null, true, pC, S.L("VT Report: %d/%d (%s)"), var7, var9, var11);
               } else if (var6 == 2L) {
                  UnitUtil.logInfo(var1, null, false, pC, S.L("VT: analysis in progress"));
               } else {
                  UnitUtil.logInfo(var1, null, false, pC, S.L("VT: error - unknown response_code"));
               }

               return true;
            }
         } else {
            return false;
         }
      }
   }
}
