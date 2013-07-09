package org.openlca.ecospold2;

import java.util.List;

import org.jdom2.Element;

public class Geography {

	private String id;
	private String shortName;
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	static Geography fromXml(Element e) {
		if (e == null)
			return null;
		Geography geography = new Geography();
		geography.setId(e.getAttributeValue("geographyId"));
		List<Element> comments = In.childs(e, "comment", "text");
		geography.setComment(In.joinText(comments));
		geography.setShortName(In.childText(e, "shortname"));
		return geography;
	}

	Element toXml() {
		Element element = new Element("geography", Out.NS);
		element.setAttribute("geographyId", id);
		Out.addChild(element, "shortname", shortName);
		if (comment != null) {
			Element commentElement = Out.addChild(element, "comment");
			Out.addIndexedText(commentElement, comment);
		}
		return element;
	}

}
