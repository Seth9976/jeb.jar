package com.pnfsoftware.jeb.corei.parsers.cert;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.ICertificateUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.encoding.Base64;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Collection;

@Ser
public class CU extends AbstractBinaryUnit implements ICertificateUnit {
   private static final ILogger q = GlobalLog.getLogger(CU.class);
   private static final String RF = "-----BEGIN CERTIFICATE-----";
   private static final String xK = "-----END CERTIFICATE-----";
   private static final byte[] Dw = Strings.encodeASCII("-----BEGIN CERTIFICATE-----");
   @SerId(1)
   private String Uv;
   @SerTransient
   private Certificate oW;

   public CU(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "cert", var1, var3, var4, var5);
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && this.oW != null;
   }

   @Override
   protected boolean processInternal() {
      byte[] var1;
      try (InputStream var2 = this.getInput().getStream()) {
         var1 = IO.readInputStream(var2);
      } catch (IOException var9) {
         return false;
      }

      if (var1.length >= Dw.length && ArrayUtil.compareBytes(var1, 0, Dw, 0, Dw.length) == 0) {
         String var11 = Strings.decodeASCII(var1);
         var11 = Strings.trim(var11);
         if (var11.endsWith("-----END CERTIFICATE-----")) {
            var11 = var11.substring("-----BEGIN CERTIFICATE-----".length(), var11.length() - "-----END CERTIFICATE-----".length());
            var11 = Strings.trim(var11);
            var11 = Strings.replaceNewLines(var11.trim(), "");

            try {
               var1 = Base64.decode(var11);
            } catch (IOException var7) {
               q.catchingSilent(var7);
            }
         }
      }

      try {
         CertificateFactory var16 = CertificateFactory.getInstance("X.509");
         ByteArrayInputStream var3 = new ByteArrayInputStream(var1);

         try {
            this.oW = var16.generateCertificate(var3);
         } catch (CertificateException var6) {
         }

         if (this.oW == null) {
            var3 = new ByteArrayInputStream(var1);
            Collection var4 = var16.generateCertificates(var3);
            if (var4.isEmpty()) {
               return false;
            }

            this.oW = (Certificate)var4.iterator().next();
         }
      } catch (CertificateException var10) {
         q.catchingSilent(var10);
         return false;
      }

      this.Uv = ej.q(this.oW);
      return true;
   }

   @Override
   public String getDescription() {
      String var1 = super.getDescription();
      var1 = var1 + "- " + S.L(S.L("Human-readable dump:\n"));
      return var1 + this.Uv;
   }

   @Override
   public Certificate getCertificate() {
      return this.oW;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new nI(this, 1L, S.L("Certificate"), true), false);
      }

      return var1;
   }
}
