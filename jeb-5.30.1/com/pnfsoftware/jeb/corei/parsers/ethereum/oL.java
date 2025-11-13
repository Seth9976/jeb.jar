package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.OptionDefinition;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class oL extends AbstractEnginesPlugin {
   private static final ILogger q = GlobalLog.getLogger(oL.class);
   private IEnginesContext RF;

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         "Etherscan Contract Fetcher",
         "Load and process EVM bytecode of contracts from one of the Ethereum networks supported by Etherscan",
         "PNF Software",
         Version.create(1, 0, 0)
      );
   }

   @Override
   public void load(IEnginesContext var1) {
      this.RF = var1;
   }

   @Override
   public void dispose() {
      super.dispose();
   }

   private String q(IEnginesContext var1, String var2) {
      try {
         return var1.getPropertyManager().getString(".etherscan." + var2);
      } catch (Exception var3) {
         return null;
      }
   }

   private boolean q(IEnginesContext var1, String var2, String var3) {
      try {
         var1.getPropertyManager().setString(".etherscan." + var2, var3);
         return true;
      } catch (Exception var4) {
         return false;
      }
   }

   @Override
   public List getExecutionOptionDefinitions() {
      return Arrays.asList(
         new OptionDefinition(null, "Provide the contract address:"),
         new OptionDefinition("ContractAddress", this.q(this.RF, "ContractAddress"), "Contract address"),
         new OptionDefinition("Network", this.q(this.RF, "Network"), "Network (optional)")
      );
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      String var3 = null;
      String var4 = null;
      if (var2 != null) {
         var3 = (String)var2.get("ContractAddress");
         var4 = (String)var2.get("Network");
      }

      if (Strings.isBlank(var3)) {
         q.error(S.L("Please provide a contract address"));
      } else {
         if (Strings.isBlank(var4)) {
            var4 = "mainnet";
         }

         this.q(var1, "ContractAddress", var3);
         this.q(var1, "Network", var4);
         this.q(var4, var3);
      }
   }

   private boolean q(String var1, String var2) {
      try {
         oL.eo var3 = this.RF(var1, var2);
         if (var3 == null) {
            q.error(S.L("Cannot retrieve contract"));
            return false;
         } else {
            q.info(S.L("Fetched contract: %s"), var3);
            String var4 = Strings.ff("Contract_%s_%s", var3.q, var3.RF);
            File var5 = var3.q(var4);
            IRuntimeProject var6 = this.RF.loadProject(var3.q);
            Artifact var7 = new Artifact(var5.getName(), new BytesInput(IO.readFile(var5)));
            var6.processArtifact(var7);
            return true;
         }
      } catch (Exception var8) {
         q.catching(var8);
         return false;
      }
   }

   private oL.eo RF(String var1, String var2) throws IOException {
      var1 = var1.trim().toLowerCase();
      var2 = var2.trim().toLowerCase();
      if (var2.startsWith("0x")) {
         var2 = var2.substring(2);
      }

      byte[] var3 = Formatter.hexStringToByteArray(var2);
      if (var3.length != 20) {
         return null;
      } else {
         var2 = "0x" + Formatter.byteArrayToHex(var3);
         String var4 = Strings.ff("https://%setherscan.io/address/%s", var1.equals("mainnet") ? "" : "." + var1, var2);
         String var5 = this.RF.getNetworkUtility().query(var4);
         q.debug(var5);
         byte[] var6 = null;
         int var7 = var5.indexOf("<div id=\"dividcode\">");
         if (var7 >= 0) {
            var7 = var5.indexOf("0x", var7);
            if (var7 >= 0) {
               int var8 = var5.indexOf("<", var7);
               if (var8 >= 0) {
                  String var9 = var5.substring(var7 + 2, var8);
                  var6 = Formatter.hexStringToByteArray(var9);
               }
            }
         }

         return var6 == null ? null : new oL.eo(var1, var2, var6);
      }
   }

   static class eo {
      String q;
      String RF;
      byte[] xK;

      public eo(String var1, String var2, byte[] var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public File q(String var1) throws IOException {
         File var2 = new File(IO.getTempFolder(), var1 + ".evm-bytecode");
         StringBuilder var3 = new StringBuilder();
         Strings.ff(var3, "0x%s\n", Formatter.byteArrayToHex(this.xK));
         Strings.ff(var3, ";contract: %s on %s\n", this.RF, this.q);
         IO.writeFile(var2, var3.toString());
         return var2;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%s:bytecode_len=%d", this.q, this.RF, this.xK.length);
      }
   }
}
