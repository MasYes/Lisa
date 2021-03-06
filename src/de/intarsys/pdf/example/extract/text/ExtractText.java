/*
 * Copyright (c) 2008, intarsys consulting GmbH
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Public License as published by the 
 * Free Software Foundation; either version 3 of the License, 
 * or (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * 
 */
package de.intarsys.pdf.example.extract.text;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import de.intarsys.pdf.content.CSDeviceBasedInterpreter;
import de.intarsys.pdf.content.CSException;
import de.intarsys.pdf.content.text.CSTextExtractor;
import de.intarsys.pdf.cos.COSVisitorException;
import de.intarsys.pdf.example.common.CommonJPodExample;
import de.intarsys.pdf.pd.PDDocument;
import de.intarsys.pdf.pd.PDPage;
import de.intarsys.pdf.pd.PDPageNode;
import de.intarsys.pdf.pd.PDPageTree;
import de.intarsys.pdf.tools.kernel.PDFGeometryTools;
import de.intarsys.tools.stream.StreamTools;

/**
 * Extract complete text from document.
 * 
 */
public class ExtractText extends CommonJPodExample {

	public static int SCALE = 2;

	public static String extr(String file) {
		ExtractText client = new ExtractText();
		try {
			return client.run(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void extractText(PDPageTree pageTree, StringBuilder sb) {
		for (Iterator it = pageTree.getKids().iterator(); it.hasNext();) {
			PDPageNode node = (PDPageNode) it.next();
			if (node.isPage()) {
				try {
					CSTextExtractor extractor = new CSTextExtractor();
					PDPage page = (PDPage) node;
					AffineTransform pageTx = new AffineTransform();
					PDFGeometryTools.adjustTransform(pageTx, page);
					extractor.setDeviceTransform(pageTx);
					CSDeviceBasedInterpreter interpreter = new CSDeviceBasedInterpreter(
							null, extractor);
					interpreter.process(page.getContentStream(), page
							.getResources());
					sb.append(extractor.getContent());
				} catch (CSException e) {
					e.printStackTrace();
				}
			} else {
				extractText((PDPageTree) node, sb);
			}
		}
	}

	protected String extractText(String filename) throws COSVisitorException,
			IOException {
		PDDocument doc = getDoc();
		StringBuilder sb = new StringBuilder();
		extractText(doc.getPageTree(), sb);
		return sb.toString();
	}

	public String run(String file) throws Exception {
		try {
			open(file);
			return extractText(file);
		} finally {
			close();
		}
	}
}
