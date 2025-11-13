package com.pnfsoftware.jeb.corei.parsers.cert;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.tree.INodeCoordinates;
import com.pnfsoftware.jeb.core.output.tree.impl.AbstractTreeDocument;
import com.pnfsoftware.jeb.core.output.tree.impl.KVNode;
import com.pnfsoftware.jeb.core.output.tree.impl.Node;
import com.pnfsoftware.jeb.core.output.tree.impl.NodeCoordinates;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SerDisabled
public class eo extends AbstractTreeDocument {
   private CU q;
   private List RF;

   public eo(CU var1) {
      if (!var1.isProcessed()) {
         throw new RuntimeException("The certificate unit was not processed");
      } else {
         this.q = var1;
         Certificate var2 = var1.getCertificate();
         if (var2 == null) {
            throw new RuntimeException();
         } else {
            this.RF = new ArrayList();
            this.RF.add(new KVNode("Type", var2.getType()));
            if (var2 instanceof X509Certificate var4) {
               this.RF.add(new KVNode("Version", var4.getVersion()));
               this.RF.add(new KVNode("Serial Number", "0x" + var4.getSerialNumber().toString(16)));
               this.RF.add(new KVNode("Subject", var4.getSubjectX500Principal()));
               KVNode var3;
               this.RF.add(var3 = new KVNode("Validity"));
               var3.addChild(new KVNode("From", var4.getNotBefore()));
               var3.addChild(new KVNode("To", var4.getNotAfter()));
               this.RF.add(var3 = new KVNode("Public Key"));
               PublicKey var5 = var4.getPublicKey();
               if (var5 instanceof RSAPublicKey var6) {
                  var3.addChild(new KVNode("Type", Strings.ff("%s %d bits", var6.getAlgorithm(), var6.getModulus().bitLength())));
                  var3.addChild(new KVNode("Exponent", var6.getPublicExponent()));
                  var3.addChild(new KVNode("Modulus", var6.getModulus()));
               } else if (var5 instanceof DSAPublicKey var11) {
                  var3.addChild(new KVNode("Type", Strings.ff("%s %d bits", var11.getAlgorithm(), var11.getY().bitLength())));
                  var3.addChild(new KVNode("y", var11.getY()));
                  var3.addChild(new KVNode("g", var11.getParams().getG()));
                  var3.addChild(new KVNode("p", var11.getParams().getP()));
                  var3.addChild(new KVNode("q", var11.getParams().getQ()));
               } else {
                  var3.addChild(new KVNode("Type", var5.getAlgorithm()));
                  var3.addChild(new KVNode("Details", var5.toString()));
               }

               this.RF.add(var3 = new KVNode("Signature"));
               var3.addChild(new KVNode("Type", var4.getSigAlgName()));
               var3.addChild(new KVNode("OID", var4.getSigAlgOID()));
               var3.addChild(new KVNode("HexData", Formatter.formatBinaryLine(var4.getSignature())));
               byte[] var12 = null;

               try {
                  var12 = var2.getEncoded();
               } catch (CertificateEncodingException var7) {
                  var1.addNotification(new UnitNotification(NotificationType.CORRUPTION, S.L("The certificate could not be encoded to its binary form")));
               }

               if (var12 != null) {
                  this.RF.add(var3 = new KVNode("Fingerprints"));
                  var3.addChild(new KVNode("MD-5", Formatter.formatBinaryLine(Hash.calculateMD5(var12))));
                  var3.addChild(new KVNode("SHA-1", Formatter.formatBinaryLine(Hash.calculateSHA1(var12))));
                  var3.addChild(new KVNode("SHA-256", Formatter.formatBinaryLine(Hash.calculateSHA256(var12))));
               }
            } else {
               this.RF.add(new KVNode("Details", var2.toString()));
            }
         }
      }
   }

   @Override
   public IUnit getUnit() {
      return this.q;
   }

   @Override
   public List getColumnLabels() {
      return Arrays.asList(S.L("Key"), S.L("Value"));
   }

   @Override
   public int getInitialExpansionLevel() {
      return -1;
   }

   @Override
   public List getRoots() {
      return this.RF;
   }

   @Override
   public String coordinatesToAddress(INodeCoordinates var1) {
      if (var1 != null && var1.getPath() != null && !var1.getPath().isEmpty()) {
         KVNode var2 = (KVNode)this.RF.get((Integer)var1.getPath().get(0));
         if (var1.getPath().size() == 1) {
            return var2.getLabel();
         } else {
            Node var3 = var2.getChild((Integer)var1.getPath().get(1));
            return var2.getLabel() + "/" + var3.getLabel();
         }
      } else {
         return null;
      }
   }

   @Override
   public INodeCoordinates addressToCoordinates(String var1) {
      if (var1 == null) {
         return null;
      } else {
         KVNode var2 = null;
         int var3 = 0;

         for (KVNode var5 : this.RF) {
            if (var1.startsWith(var5.getLabel())) {
               var1 = var1.substring(var5.getLabel().length());
               var2 = var5;
               break;
            }

            var3++;
         }

         if (var2 == null) {
            return null;
         } else {
            if (var1.startsWith("/")) {
               var1 = var1.substring(1);
            }

            if (var1.isEmpty()) {
               return new NodeCoordinates(Arrays.asList(var3));
            } else {
               int var7 = 0;

               for (Node var6 : var2.getChildren()) {
                  if (var1.equals(var6.getLabel())) {
                     return new NodeCoordinates(Arrays.asList(var3, var7));
                  }

                  var7++;
               }

               return new NodeCoordinates(Arrays.asList(var3));
            }
         }
      }
   }
}
