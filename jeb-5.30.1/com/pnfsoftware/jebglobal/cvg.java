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
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class cvg extends AbstractEnginesPlugin {
   private static final ILogger q = GlobalLog.getLogger(cvg.class);
   private IEnginesContext RF;
   private IEventListener xK;
   private String Dw;
   private static final String Uv = ".VirusTotalApiKey";

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
      this.RF = var1;
      this.Dw = this.q(var1);
      this.xK = new cvh(this);
      var1.addListener(this.xK);
   }

   @Override
   public void dispose() {
      if (this.xK != null) {
         this.RF.removeListener(this.xK);
         this.xK = null;
      }
   }

   @Override
   public List getExecutionOptionDefinitions() {
      return Arrays.asList(
         new OptionDefinition(null, S.L("Specify or update your VirusTotal API key:")), new OptionDefinition("apikey", this.Dw, S.L("VirusTotal API Key"))
      );
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      if (var2 != null) {
         this.Dw = (String)var2.get("apikey");
         if (this.Dw != null) {
            this.q(var1, this.Dw);
         }
      }

      if (this.Dw == null) {
         q.error(S.L("In order to use the VirusTotal Scan Report plugin, set up your VT API key first!"));
      } else {
         this.RF(var1);
      }
   }

   private String q(IEnginesContext var1) {
      try {
         return var1.getPropertyManager().getString(".VirusTotalApiKey");
      } catch (Exception var2) {
         return null;
      }
   }

   private boolean q(IEnginesContext var1, String var2) {
      try {
         var1.getPropertyManager().setString(".VirusTotalApiKey", var2);
         return true;
      } catch (Exception var3) {
         return false;
      }
   }

   private void RF(IEnginesContext var1) {
      for (IRuntimeProject var3 : var1.getProjects()) {
         for (ILiveArtifact var5 : var3.getLiveArtifacts()) {
            for (IUnit var7 : var5.getUnits()) {
               if (var7 instanceof IBinaryUnit) {
                  try {
                     this.q((IBinaryUnit)var7);
                  } catch (Exception var9) {
                     q.catching(var9);
                  }
               }
            }
         }
      }
   }

   private boolean q(IBinaryUnit var1) throws IOException, ParseException {
      if (Strings.isBlank(this.Dw)) {
         return false;
      } else {
         String var2;
         try (InputStream var3 = var1.getInput().getStream()) {
            byte[] var4 = com.pnfsoftware.jeb.util.io.IO.readInputStream(var3);
            var2 = Formatter.byteArrayToHexString(Hash.calculateSHA256(var4)).toLowerCase();
         }

         q.debug(S.L("Verifying SHA-256 hash on VirustTotal: %s"), var2);
         String var14 = Strings.ff("https://www.virustotal.com/vtapi/v2/file/report?apikey=%s&resource=%s", this.Dw, var2);
         String var15 = this.RF.getNetworkUtility().query(var14);
         q.debug(var15);
         if (var15 != null && !var15.isEmpty()) {
            Map var5 = (Map)new JSONParser().parse(var15);
            Long var6 = (Long)var5.get("response_code");
            if (var6 == null) {
               q.debug(S.L("Invalid VT answer: %s"), var14);
               return false;
            } else {
               if (var6 == 0L) {
                  UnitUtil.logInfo(var1, null, true, q, S.L("VT: unknown file"));
               } else if (var6 == 1L) {
                  long var7 = (Long)var5.get("positives");
                  long var9 = (Long)var5.get("total");
                  String var11 = (String)var5.get("scan_date");
                  UnitUtil.logInfo(var1, null, true, q, S.L("VT Report: %d/%d (%s)"), var7, var9, var11);
               } else if (var6 == 2L) {
                  UnitUtil.logInfo(var1, null, false, q, S.L("VT: analysis in progress"));
               } else {
                  UnitUtil.logInfo(var1, null, false, q, S.L("VT: error - unknown response_code"));
               }

               return true;
            }
         } else {
            return false;
         }
      }
   }
}
