package org.openlca.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dq_systems")
public class DQSystem extends CategorizedEntity {

	@Column(name = "has_uncertainties")
	public boolean hasUncertainties;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "f_dq_system")
	public List<DQIndicator> indicators = new ArrayList<>();
	
	@Override
	public DQSystem clone() {
		DQSystem clone = new DQSystem();
		Util.cloneRootFields(this, clone);
		clone.hasUncertainties = hasUncertainties;
		return clone;
	}
	
	public int getScoreCount() {
		if (indicators == null || indicators.isEmpty())
			return 0;
		return indicators.get(0).scores.size();
	}

}
