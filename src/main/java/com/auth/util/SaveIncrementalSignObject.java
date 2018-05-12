package com.auth.util;

import java.io.FileOutputStream;

public class SaveIncrementalSignObject
{
  private String a;
  private String b;
  private FileOutputStream c;
  private org.apache.pdfbox.pdmodel.PDDocument d;
  
  public SaveIncrementalSignObject() {}
  
  public String getPdfNameForSigning()
  {
    return a;
  }
  
  public void setPdfNameForSigning(String pdfNameForSigning) { a = pdfNameForSigning; }
  
  public String getPdfTempPathForSigning() {
    return b;
  }
  
  public void setPdfTempPathForSigning(String pdfTempPathForSigning) { b = pdfTempPathForSigning; }
  
  public FileOutputStream getFos() {
    return c;
  }
  
  public void setFos(FileOutputStream fos) { c = fos; }
  
  public org.apache.pdfbox.pdmodel.PDDocument getPDDocumentFromFile() {
    return d;
  }
  
  public void setPDDocumentFromFile(org.apache.pdfbox.pdmodel.PDDocument pDDocumentFromFile) { d = pDDocumentFromFile; }
}
