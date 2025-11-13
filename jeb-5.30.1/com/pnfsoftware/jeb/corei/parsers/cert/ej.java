package com.pnfsoftware.jeb.corei.parsers.cert;

import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.ByteArrayInputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;

public class ej {
   public static boolean q(byte[] var0) {
      return q("X.509", var0);
   }

   public static boolean q(String var0, byte[] var1) {
      try {
         CertificateFactory var2 = CertificateFactory.getInstance(var0);

         try {
            var2.generateCertificate(new ByteArrayInputStream(var1));
            return true;
         } catch (CertificateException var3) {
            var2.generateCertificates(new ByteArrayInputStream(var1));
            return true;
         }
      } catch (CertificateException var4) {
         return false;
      }
   }

   public static String q(Certificate var0) {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "Type: %s\n", var0.getType());
      if (var0 instanceof X509Certificate var2) {
         String var3 = Strings.ff(
            "Version: %d\nSerial Number: 0x%s\nIssuer: %s\nValidity: from = %s\n            to = %s\nSubject: %s\n\nPublic Key:\n%s\nSignature:\n  type = %s, OID = %s\n  hexdata = %s\n",
            var2.getVersion(),
            var2.getSerialNumber().toString(16),
            var2.getIssuerX500Principal(),
            var2.getNotBefore(),
            var2.getNotAfter(),
            var2.getSubjectX500Principal(),
            q(var2.getPublicKey()),
            var2.getSigAlgName(),
            var2.getSigAlgOID(),
            Formatter.formatBinaryLine(var2.getSignature())
         );
         var1.append(var3);
      } else {
         var1.append(var0.toString());
      }

      byte[] var7 = null;

      try {
         var7 = var0.getEncoded();
      } catch (CertificateEncodingException var6) {
      }

      if (var7 != null) {
         CharSequence var8 = Formatter.formatBinaryLine(Hash.calculateMD5(var7));
         CharSequence var4 = Formatter.formatBinaryLine(Hash.calculateSHA1(var7));
         CharSequence var5 = Formatter.formatBinaryLine(Hash.calculateSHA256(var7));
         Strings.ff(var1, "\nMD5 Fingerprint: %s\nSHA-1 Fingerprint: %s\nSHA-256 Fingerprint: %s\n", var8, var4, var5);
      }

      return var1.toString();
   }

   public static String q(PublicKey var0) {
      StringBuilder var1 = new StringBuilder();
      if (var0 instanceof RSAPublicKey var2) {
         Strings.ff(
            var1,
            "  type = %s %d bits\n  exponent = %s\n  modulus = %s",
            var2.getAlgorithm(),
            var2.getModulus().bitLength(),
            var2.getPublicExponent(),
            var2.getModulus()
         );
      } else if (var0 instanceof DSAPublicKey var3) {
         Strings.ff(
            var1,
            "  type = %s %d bits\n  y = %s\n  g = %s\n  p = %s\n  q = %s",
            var3.getAlgorithm(),
            var3.getY().bitLength(),
            var3.getY(),
            var3.getParams().getG(),
            var3.getParams().getP(),
            var3.getParams().getQ()
         );
      } else {
         Strings.ff(var1, "  %s", var0.toString());
      }

      return var1.toString();
   }
}
