package main

import (
	"encoding/csv"
	"fmt"
	"log"
	"os"
	"strings"

	"gopkg.in/yaml.v2"
)

// Define a struct to hold the data from each line of the CSV
type Record struct {
	TargetType            string
	TargetMember          string
	HeaderForAssemblyName string
	DotNetCore            string
	RecommendedChanges    string
	IssueID               string
	Category              string
	Severity              string
	Recommendation        string
	JSONInputFile         string
	OverHeadCost          string
	OccurenceCost         string
	// Add other fields as necessary
}

type Rule struct {
	RuleID      string   `yaml:"ruleID"`
	Category    string   `yaml:"category"`
	Effort      int      `yaml:"effort"`
	Labels      []string `yaml:"labels"`
	Message     string   `yaml:"message"`
	Description string   `yaml:"description"`
	When        When     `yaml:"when"`
}

type When struct {
	DotnetReferenced DotnetReferenced `yaml:"dotnet.referenced"`
}

type DotnetReferenced struct {
	Namespace string `yaml:"namespace"`
	Pattern   string `yaml:"pattern"`
}

func main() {
	// Open the CSV file
	file, err := os.Open("DOTNET_RULES.csv")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	// Create a new CSV reader
	reader := csv.NewReader(file)

	// Read the header line (first line)
	header, err := reader.Read()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("CSV Header:", header)

	var rules []Rule
	records := make(map[string]interface{})
	counter := 1
	for {
		line, err := reader.Read()
		if err != nil {
			break // End of file
		}

		// Create a new Record from the line
		record := Record{
			TargetType:            line[0],
			TargetMember:          line[1],
			HeaderForAssemblyName: line[2],
			DotNetCore:            line[3],
			RecommendedChanges:    line[4],
			IssueID:               line[5],
			Category:              line[6],
			Severity:              line[7],
			Recommendation:        line[8],
			JSONInputFile:         line[9],
			OverHeadCost:          line[10],
			OccurenceCost:         line[11],
			// Map other fields as necessary
		}
		if record.HeaderForAssemblyName == "" {
			continue
		}

		// Prevent dupes
		if _, ok := records[record.HeaderForAssemblyName]; ok {
			fmt.Println("Seen this one already")
			continue
		}
		records[record.HeaderForAssemblyName] = record

		namespace := ""
		pattern := record.HeaderForAssemblyName
		headerSplit := strings.Split(record.HeaderForAssemblyName, ".")
		if len(headerSplit) > 1 {
			pattern = headerSplit[len(headerSplit)-1]
			headerSplit = headerSplit[:len(headerSplit)-1]
			namespace = strings.Join(headerSplit, ".")
		}

		rules = append(rules, Rule{
			Category: "potential",
			Effort:   1,
			Labels: []string{
				"konveyor.io/source=dotnetframework",
				"konveyor.io/target=dotnet3",
				"konveyor.io/generated=true",
			},
			Message:     record.RecommendedChanges,
			Description: record.RecommendedChanges,
			RuleID:      fmt.Sprintf("dotnet-rules-converted-%05d", counter),
			When: When{
				DotnetReferenced: DotnetReferenced{
					Namespace: namespace,
					Pattern:   pattern,
				},
			},
		})
		counter++
	}

	// Marshal the rules into YAML
	yamlData, err := yaml.Marshal(&rules)
	if err != nil {
		log.Fatalf("error: %v", err)
	}

	// Write the YAML to a file
	err = os.WriteFile("../../preview/dotnet/generated.yaml", yamlData, 0644)
	if err != nil {
		log.Fatal(err)
	}

}
